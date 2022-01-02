$(document).ready(function () {
    getSupplyAndBook();
})


function valueFilledCheck(a, b) {
    if( a==='' || b==='') {
        alert('빈칸 없이 입력해주세요.');
        return false;
    }
    return true;
}
function supplyAndBookEnroll() {
    let supplyId = document.querySelector('.supplyAndBook-1').value;
    let bookId = document.querySelector('.supplyAndBook-2').value;

    if ((valueFilledCheck(supplyId, bookId))) {
        getSupplyAndBookData(supplyId, bookId);
    }
}

function getSupplyAndBook() {
    let supplyBookItem = document.querySelector('.contract-body');
    supplyBookItem.innerHTML = "";

    $.ajax({
        type: "GET",
        url: "/supplybook",
        contentType: "application/json",
        success: function (res) {
            console.log(res);
            for(let i = 0; i < res.length; i++) {
                let template = `
                   <div class="${res[i].id}-supBook supBook-block">                    
                        <div class="supBook-item ${res[i].id}-supBook">공급과 도서 고유번호</div>                        
                        <div class="supBook-item ${res[i].id}-supId">${res[i].supply.id}</div>
                        <div class="supBook-item supBook-3 ${res[i].id}-BookId">${res[i].book.id}</div>
                    <div>            
                    <button type="button" onclick ="openEditSupBook(${res[i].id})" class="btn btn-primary ${res[i].id}-supBook-edit">수정</button>
                    <button type="button" onclick ="deleteSupBook(${res[i].id})" class="btn btn-danger ${res[i].id}-supBook-delete">삭제</button>                                                                                                                                   
                `
                supplyBookItem.innerHTML += template;
            }

        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "GET ERROR 발생");
        }
    })
}

function getSupplyAndBookData(supplyId, bookId) {
    $.ajax({
        type: "POST",
        url: `/supplybook/${supplyId}/${bookId}`,
        contentType: "application/json",
        data: JSON.stringify({
            supply: supplyId,
            book: bookId,
        }),
        success: function (res) {
            console.log(res);
            alert('공급 및 도서 고유번호가 등록되었습니다.');
            location.href="supply_book.html";
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}