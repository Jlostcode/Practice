document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        locale: 'ko',
        headerToolbar: {
            left: 'prev next',
            center: 'title',
            right: 'dayGridMonth dayGridWeek'
        },
        buttonText: {
            month: "월별",
            week: "주별"
        },
        events: function (info, successCallback, failureCallback) {
            // console.log('startStr=' + info.startStr.substring(0, 10))
            // console.log('endStr=' + info.endStr.substring(0, 10))

            $.ajax({
                type: 'GET',
                url: "/user/getScheduleList",
                data: {
                    start_time: info.startStr.substring(0, 10),
                    end_time: info.endStr.substring(0, 10)
                },
                success: function (response) {
                    calendar.removeAllEvents();
                    var events = [];
                    if (response != null) {
                        $.each(response.scheduleList, function (index, ele) {
                            // console.log(ele.title, ele.start_time, ele.end_time)
                            events.push({
                                title: ele.title
                                , start: ele.start_time
                                , end: ele.end_time

                            });
                        });
                    }
                    successCallback(events);
                }
            });
        },
        dateClick: function (info) {
            var selectDate = info.dateStr;
            var selectday = selectDate + " (" + getdays(selectDate) + ")";
            $('#scheduleModal').modal('show').fadeIn(400);
            $('#eventName').val(''); // 텍스트 영역 초기화
            $('#date').val(selectDate);
            $('#eventDate').html(selectday);
            $('#start_time').val();
            $('#end_time').val('23:59');

            $('.titlecount').keyup(function (e) {
                var content = $(this).val();
                // console.log(content.length)console.log(content.length)
                $('#counter').html("(" + content.length + " / 최대 10자)"); //글자수 실시간 카운팅
                // 글자수 제한
                if (content.length > 10) {
                    // 10자 넘으면 알림창 뜨도록
                    alert('글자수는 10자까지 입력 가능합니다.');
                    // 10자 부터는 타이핑 되지 않도록
                    $(this).val($(this).val().substring(0, 10));
                    $('#counter').html("(10 / 최대 10자)");
                }
                if (content.length === 0) {
                    $('#counter').html("(0 / 최대 10자)");
                }
            });
        },
        eventClick: function (info) {
            var cnt = eventDevare(info);
            if (cnt > 0) info.event.remove();
        },


    });
    calendar.render();

    $('#saveEventBtn').on('click', function () {
        // 입력된 일정명과 날짜를 가져옵니다.
        var eventName = $('#eventName').val();
        var eventDate = $('#eventDate').val();

        // 새로운 이벤트 객체를 생성합니다.
        var newEvent = {
            title: eventName,
            start: eventDate
        };
        var title = $('#eventName').val();
        var date = $('#date').val();
        var startTime = $('#start_time').val();
        var endTime = $('#end_time').val();
        console.log(date, startTime,endTime)
        if (title == null || title == "") {
            alert("일정을 기입바랍니다.");
            $('#eventName').focus();
            return;
        }
        if (confirm(title + " 일정 등록하시겠습니까?")) {
            $.ajax({
                type: "GET",
                url: "/user/insertSchedule",
                data: {
                    title: title,
                    start_time: date + " " + startTime,
                    end_time: date + " " + endTime
                },
                async: false,
                success: function (data) {
                    if (data.result > 0)
                        calendar.addEvent({
                            title: title,
                            start: date + " " + startTime,
                            end: date + " " + endTime
                        });
                    // 캘린더에 새로운 이벤트를 추가합니다.
                    calendar.addEvent(newEvent);

                    // 모달을 닫습니다.
                    $('#scheduleModal').modal('hide');

                    // 입력 필드를 비웁니다.
                    $('#eventName').val('');
                    $('#eventDate').val('');
                    location.reload();
                },
                beforeSend: function () {
                    $('.wrap-loading-box').removeClass('display-none');
                },
                compvare: function () {
                    $('.wrap-loading-box').addClass('display-none');
                },
                error: function (e) {
                    alert("등록에 실패하였습니다\n개발자에게 문의해주세요");
                }
            });
        }

    });

    function eventDevare(info) {
        var result;
        var title = info.event.title;
        var startTime = formatTime(info.event.start);
        var endTime = formatTime(info.event.end);
        console.log(startTime, endTime)
        if (confirm("[ " + title + " ]\n일정을 삭제하시겠습니까?")) {
            $.ajax({
                type: "GET",
                url: "/user/devareSchedule",
                data: {
                    title: title,
                    start_time: startTime,
                    end_time: endTime,
                },
                async: false,
                success: function (data) {
                    result = data.result;
                    console.log(result)
                    location.reload();
                },
                beforeSend: function () {
                    $('.wrap-loading-box').removeClass('display-none');
                },
                compvare: function () {
                    $('.wrap-loading-box').addClass('display-none');
                },
                error: function (e) {
                    alert("삭제에 실패하였습니다\n개발자에게 문의해주세요");
                }
            });
        }
        return result;
    }
});
function getdays(date) {
    var week = new Array('일', '월', '화', '수', '목', '금', '토');
    var tmpDate = new Date(date);
    var day = week[tmpDate.getDay()];
    return day;
}

function formatTime(date){
    var d = new Date(date);
    return d.getFullYear() + "-" + ("0"+(1+d.getMonth())).slice(-2) + "-" + ("0"+(d.getDate())).slice(-2) + " " + d.getHours() + ":" + ("0"+(d.getMinutes())).slice(-2);
}
