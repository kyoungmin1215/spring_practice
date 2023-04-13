// https://api.finance.naver.com/service/itemSummary.nhn?itemcode=091990
// "https://finance.naver.com/item/item_right_ajax.naver?type=recent&code=091990&page=1"

// const info = fetch("https://api.finance.naver.com/service/itemSummary.nhn?itemcode=091990")
//     .then((response) => response.json())
//     .then((data) => console.log(data));


// json 파일 return
function getFinanceInfo(code) {
    const url = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=" + code;

    try {
        return fetch(`https://cors-anywhere.herokuapp.com/${url}`).then((res) => res.json());
    } catch(err) {
        alert("error");
    }
    return null;
}

async function getNowValue(code) {

    let financeInfo = await getFinanceInfo(code);
    // let financeInfo = setInterval(await getFinanceInfo(code),60000);
    // let financeInfo = setInterval(async function() {
    //     await getFinanceInfo(code);
    // }, 20000);

    console.log(financeInfo);

    const financeName = "셀트리온헬스케어"
    let now_val = financeInfo.now

    let financeArr = [];

    // 현재 시간
    let date = new Date();
    current_time = date.toLocaleString();

    financeArr.push([financeName, now_val, current_time]);
    console.log(financeArr);

    // 테이블에 보낼 거 시작
    let financeTable = document.getElementById("financeTable");

    // while(financeTable.rows.length > 2) {
    //     financeTable.deleteRow(financeTable.rows.length);
    // }

    // for (i=0; i<financeArr.length; i++) {
    //     row = financeTable.insertRow();
    //     for (each=0; each<financeArr[i].length; each++) {
    //         const cell = row.insertCell();
    //         cell.innerHTML = financeArr[i][each];
    //     }
    // }

    financeArr.forEach(finance => {
        const row = financeTable.insertRow();
        row.insertCell().innerHTML = finance[0];
        row.insertCell().innerHTML = finance[1];
        row.insertCell().innerHTML = finance[2];
    });
    // 테이블에 보낼 거 종료

}

// const set_time = 60000;
// setInterval(getNowValue("091990"),set_time);
// setTimeout(getNowValue("091990"), 60000);
// getNowValue("091990");

// setInterval(getNowValue("091990"),20000);

setInterval(async function() {
    await getNowValue("091990");
}, 20000);


