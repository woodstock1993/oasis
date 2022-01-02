let contractId = "";

$(document).ready(function () {
    paintContract();
    console.log(contractId);
    getSupplies(contractId);
})

function findIndex(query) {
    for (let i = 0; i < query.length; i++) {
        if (query[i] === '=') {
            return i;
        }
    }
}

function valueFilledCheck2(a) {
    if( a==='') {
        alert('빈칸 없이 입력해주세요.');
        return false;
    }
    return true;
}

function editSupply(id) {
    let supplyDate = document.querySelector('.i-contract-2').value;
    if (!valueFilledCheck2(supplyDate)) {
        return
    }

    $.ajax({
        type: "PUT",
        url: `/supplies/${id}`,
        contentType: "application/json",
        data: JSON.stringify({
            supplyDate: `${supplyDate}`,
        }),
        success: function (res) {
            console.log(res);
            alert(`공급번호${id}의 공급일자가 수정되었습니다.`)
            location.href = `/supplies.html?id=${contractId}`
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "PUT ERROR 발생");
        }
    })
}

function openEditSupply(id) {
    let modal = document.querySelector('.edit-modal');
    let modalEditInput = document.querySelector('.modal-edit-input');
    let supplyNumber = document.getElementsByClassName(`${id}-supply-id`)[0].textContent;
    let supplyDate = document.getElementsByClassName(`${id}-supply-date`)[0].textContent;

    modalEditInput.innerHTML = ""
    let template = `
            <div class="input-group">
                <span class="input-group-text">계약</span>
                <input type="text" aria-label="공급" placeholder="공급" class="form-control i-supply-4" disabled>
                <input type="text" aria-label="공급번호" placeholder="${supplyNumber}" class="form-control i-contract-1" disabled>
                <input type="text" aria-label="공급일자" placeholder="${supplyDate}" class="form-control i-contract-2">
                <div class="d-grid gap-2 d-md-block">
                    <button class="btn btn-primary" type="button" onclick="editSupply(${id})">수정</button>                    
                    <button class="btn btn-primary" type="button" onclick="closeSupply()">닫기</button>
                </div>                                
            </div>    
    `
    modalEditInput.innerHTML = template;
    modal.classList.remove("hidden");
}

function deleteSupply(id) {
    $.ajax({
        type: "DELETE",
        url: `/supplies/${id}`,
        success: function (response) {
            console.log(`id ${response} is deleted`);
            alert(`공급번호${id}가 삭제 되었습니다.`)
            location.href = `/supplies.html?id=${contractId}`;
        }
    })
}

function getSupplies(id) {
    let supplyBlockIndex = document.querySelector('.supply-block-index-2');
    supplyBlockIndex.innerHTML = "";

    $.ajax({
        type: "GET",
        url: `/supplies/${id}`,
        contentType: "application/json",
        success: function (res) {
            console.log(res);
            console.log(`length: ${res.length}`);
            for(let i = 0; i < res.length; i++) {
                let template = `
                <div class="${res[i].id}-supply supply-block">                    
                    <div class="supply-item ${res[i].id}-supply">공급</div>                        
                    <div class="supply-item ${res[i].id}-supply-id">${res[i].id}</div>
                    <div class="supply-item ${res[i].id}-supply-date">${res[i].supplyDate}</div>
                <div>
                <button type="button" onclick ="openEditSupply(${res[i].id})" class="btn btn-primary ${res[i].id}-contract-edit">수정</button>
                <button type="button" onclick ="deleteSupply(${res[i].id})" class="btn btn-danger ${res[i].id}-contract-delete">삭제</button>                                                                                                           
                `
                supplyBlockIndex.innerHTML += template;
            }
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "GET ERROR 발생");
        }
    })
}

// id -> 계약 고유번호
function sendSupplyData(id) {
    let supplyDate = document.querySelector('.supply-date').value;
    if (!(valueFilledCheck2(supplyDate))) {
        return
    }

    $.ajax({
        type: "POST",
        url: `/supplies/${id}`,
        contentType: "application/json",
        data: JSON.stringify({
            supplyDate: `${supplyDate}`,
        }),
        success: function (res) {
            console.log(res);
            alert(`계약번호${id}과 연관된 공급이 등록되었습니다.`)
            location.href = `/supplies.html?id=${id}`
        },
        error: function(request, status, error) {
            console.log(request);
            console.log(status);
            console.log(error);
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}


function closeSupply() {
    let modal = document.querySelector('.edit-modal');
    modal.classList.add('hidden');
}

function createSupply(id) {
    let modal = document.querySelector('.edit-modal');
    let modalEditInput = document.querySelector('.modal-edit-input');
    let cNumber = document.getElementsByClassName(`${id}-contract-number`)[0].textContent;

    modalEditInput.innerHTML =
        `<div class="supply-input-box">
            <div class="input-group">
                <span class="input-group-text">공급</span>                                
                <input type="text" aria-label="공급일자" placeholder="공급일자" class="form-control supply-date">
                <input type="text" aria-label="계약번호" placeholder="계약번호: ${cNumber}" class="form-control ${id}-contract-num" disabled>                
                <div class="d-grid gap-2 d-md-block">
                    <button class="btn btn-primary" type="button" onclick="sendSupplyData(${id})">등록</button>                    
                    <button class="btn btn-primary" type="button" onclick="closeSupply()">닫기</button>
                </div>
            </div>
        </div>`

    modal.classList.remove("hidden");
}

function paintContract() {
    let supplyBlockIndex = document.querySelector('.supply-block-index');
    const IdAndValue = window.location.search
    const index = findIndex(IdAndValue);
    const id = IdAndValue.substring(index + 1,);
    contractId = id;
    console.log(`id: ${id}`);

    $.ajax({
        type: "GET",
        url: `/contracts/${id}`,
        contentType: "application/json",
        success: function (res) {
            console.log(res)
                let template = `
                <div class="${res.id}-supply supply-block">                    
                    <div class="contract-item ${res.id}-contract-number">${res.id}</div>
                    <div class="contract-item ${res.id}-contract-company">${res.contractCompany}</div>
                    <div class="contract-item ${res.id}-contract-date">${res.contractDate}</div>
                    <div class="contract-item ${res.id}-contract-min-ratio">${res.minPriceRatio}</div>
                    <div class="contract-item ${res.id}-contract-code">${res.contractCode}</div>
                    <button type="button" onclick ="createSupply(${res.id})" class="btn btn-primary ${res.id}-supply-enroll">등록</button>
                <div>
                `
                supplyBlockIndex.innerHTML = "";
                supplyBlockIndex.innerHTML += template;
        },
    })
}
