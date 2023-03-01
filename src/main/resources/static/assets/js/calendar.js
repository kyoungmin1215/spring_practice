
var today = new Date();

function buildCalendar(){
  var row = null
  var calendarTable = document.getElementById("calendarTable");
  var calendarTableTitle = document.getElementById("calendarTitle");
  calendarTableTitle.innerHTML = today.getFullYear()+"년"+(today.getMonth()+1)+"월";
  
  var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);

  row = calendarTable.insertRow();

  for(i = 1; i <= lastDate.getDate(); i++){
    if (cnt % 7 == 1) {
    	cell.innerHTML = "<font color=#F79DC2>" + i + "</font>";
    }

    if (cnt % 7 == 0){
    	cell.innerHTML = "<font color=skyblue>" + i + "</font>";
    	row = calendar.insertRow();
    }
    cell = row.insertCell();
    cell.innerHTML = i;
    cell.align = "center";
  }

}

function prevCalendar(){
	today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
	buildCalendar();
}

function nextCalendar(){
	today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
	buildCalendar();
}