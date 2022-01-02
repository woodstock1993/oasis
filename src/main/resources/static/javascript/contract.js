$(document).ready(function () {
    getContracts();
})

function valueFilledCheck(a, b, c, d) {
    if( a==='' || b==='' || c==='' || d==='') {
        alert('빈칸 없이 입력해주세요.');
        return false;
    }
    return true;
}

function getContracts() {
    let contractBody = document.querySelector('.contract-body');
    contractBody.innerHTML = "";

    $.ajax({
        type: "GET",
        url: "/contracts",
        contentType: "application/json",
        success: function (res) {
            for(let i = 0; i < res.length; i++) {
                let template = `
                <div class="${res[i].id}-contract contract-block">                    
                    <div class="contract-item ${res[i].id}-contract-number">${res[i].id}</div>
                    <div class="contract-item ${res[i].id}-contract-company">${res[i].contractCompany}</div>
                    <div class="contract-item ${res[i].id}-contract-date">${res[i].contractDate}</div>
                    <div class="contract-item ${res[i].id}-contract-min-ratio">${res[i].minPriceRatio}</div>
                    <div class="contract-item ${res[i].id}-contract-code">${res[i].contractCode}</div>
                <div>
                <a href="supplies.html?id=${res[i].id}"><button type="button" class="btn btn-success ${res[i].id}-contract-lookup">공급조회</button></a>
                <button type="button" onclick ="openEditContract(${res[i].id})" class="btn btn-primary ${res[i].id}-contract-edit">수정</button>
                <button type="button" onclick ="deleteContract(${res[i].id})" class="btn btn-danger ${res[i].id}-contract-delete">삭제</button>                                                                                                           
                `
                contractBody.innerHTML += template;
            }
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}

function sendContractData() {
    let contractDate = document.querySelector('.contract-1').value;
    let minPriceRatio = document.querySelector('.contract-2').value;
    let contractCode = document.querySelector('.contract-3').value;
    let contractCompany = document.querySelector('.contract-4').value;

    $.ajax({
        type: "POST",
        url: "/contracts",
        contentType: "application/json",
        data: JSON.stringify({
            contractDate: `${contractDate}`,
            minPriceRatio: `${minPriceRatio}`,
            contractCode: `${contractCode}`,
            contractCompany: `${contractCompany}`
        }),
        success: function (res) {
            console.log(res);
            alert('계약이 등록 되었습니다.')
            location.href = "index.html"
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}

function contractEnroll() {
    let contractDate = document.querySelector('.contract-1').value;
    let minPriceRatio = document.querySelector('.contract-2').value;
    let contractCode = document.querySelector('.contract-3').value;
    let contractCompany = document.querySelector('.contract-4').value;
    if (valueFilledCheck(contractDate, minPriceRatio, contractCode, contractCompany) === true) {
        sendContractData();
    }
}

function openEditContract(id) {
    let modal = document.querySelector('.edit-modal');
    let modalEditInput = document.querySelector('.modal-edit-input');
    let c_date = document.getElementsByClassName(`${id}-contract-date`)[0].textContent;
    let c_ratio = document.getElementsByClassName(`${id}-contract-min-ratio`)[0].textContent;
    let c_code = document.getElementsByClassName(`${id}-contract-code`)[0].textContent;
    let c_company = document.getElementsByClassName(`${id}-contract-company`)[0].textContent;

    modalEditInput.innerHTML =
        `<div class="contract-input-box">
            <div class="input-group">
                <span class="input-group-text">계약</span>
                <input type="text" aria-label="계약업체" placeholder="${c_company}" class="form-control i-contract-4">
                <input type="text" aria-label="계약일자" placeholder="${c_date}" class="form-control i-contract-1">
                <input type="text" aria-label="최저가비율" placeholder="${c_ratio}" class="form-control i-contract-2">
                <input type="text" aria-label="계약상태코드" placeholder="${c_code}" class="form-control i-contract-3">
                <div class="d-grid gap-2 d-md-block">
                    <button class="btn btn-primary" type="button" onclick="editContract(${id})">수정</button>                    
                    <button class="btn btn-primary" type="button" onclick="closeContract()">닫기</button>
                </div>
            </div>
        </div>`

    modal.classList.remove("hidden");
}

function closeContract() {
    let modal = document.querySelector('.edit-modal');
    modal.classList.add('hidden');
}

function editContract(id) {
    let contractCompany = document.querySelector('.i-contract-4').value;
    let contractDate = document.querySelector('.i-contract-1').value;
    let minPriceRatio = document.querySelector('.i-contract-2').value;
    let contractCode = document.querySelector('.i-contract-3').value;

    $.ajax({
        type: "PUT",
        url: `/contracts/${id}`,
        contentType: "application/json",
        data: JSON.stringify({
            contractDate: `${contractDate}`,
            minPriceRatio: `${minPriceRatio}`,
            contractCode: `${contractCode}`,
            contractCompany: `${contractCompany}`
        }),
        success: function (res) {
            console.log(res);
            alert('계약이 업데이트 되었습니다.')
            location.href = "index.html"
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "PUT ERROR 발생");
        }
    })
}

function deleteContract(id) {
    $.ajax({
        type: "DELETE",
        url: `/contracts/${id}`,
        success: function (response) {
            console.log(`id ${response} is deleted`);
            alert('계약내용이 삭제 되었습니다.')
            location.href = "index.html"
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "해당 계약과 관련된 공급데이터가 있습니다.");
        }
    })

}