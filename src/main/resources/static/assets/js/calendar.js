const date = new Date();
const today = {
    "year": date.getFullYear(),
    "month": date.getMonth(),
    "date": date.getDate()
}
let now = date;


function buildCalendar() {
    const year = now.getFullYear();
    const month = now.getMonth();
    const last = new Date(year, month+1, 0);
    const first = new Date(year, month, 0);

    const lastDate = last.getDate();
    const firstDay = first.getDay() + 1;

    const calendarTable = document.getElementById("calendarTable");
    const calendarTitle = document.getElementById("calendarTitle");
    calendarTitle.innerHTML = year + "년 " + (month + 1) + "월";

    while(calendarTable.rows.length > 1) {
        calendarTable.deleteRow(calendarTable.rows.length -1);
    }

    for (i = 1; i <= lastDate + firstDay; i++) {
        if(i % 7 == 1) {
            row = calendarTable.insertRow();
        }

        const cell = row.insertCell();

        if(i <= firstDay) {
            cell.innerHTML = "";
        } else {
            if(i-firstDay == date.getDate() && month == today["month"] && year == today["year"]) {
                cell.innerHTML = "<font color=orange>" + (i - firstDay) + "</font>";
            } else {
                cell.innerHTML = i - firstDay;
            }
        }
    }
}

function prevCalendar(){
	now = new Date(now.getFullYear(), now.getMonth()-1, now.getDate());
	buildCalendar();
}

function nextCalendar(){
	now = new Date(now.getFullYear(), now.getMonth()+1, now.getDate());
	buildCalendar();
}

buildCalendar();