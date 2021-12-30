$(document).ready(function () {
    getContracts();
})

function valueFilledCheck(a, b, c) {
    if( a==='' || b==='' || c==='') {
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
                <tr>
                  <th scope="row">${res[i].id}</th>
                  <td>${res[i].contractDate}</td>
                  <td>${res[i].minPriceRatio}</td>
                  <td>${res[i].contractCode}</td>
                </tr>`
                contractBody.innerHTML += template;
            }
        },
        error: function(request, status, error) {
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}

function sendContractData() {
    let contractDate = document.querySelector('.contract-1').value;
    let minPriceRatio = document.querySelector('.contract-2').value;
    let contractCode = document.querySelector('.contract-3').value;
    let contractBody = document.querySelector('.contract-body');

    $.ajax({
        type: "POST",
        url: "/contracts",
        contentType: "application/json",
        data: JSON.stringify({
            contractDate: `${contractDate}`,
            minPriceRatio: `${minPriceRatio}`,
            contractCode: `${contractCode}`
        }),
        success: function (res) {
            console.log(res);
            let template = `
                <tr>
                  <th scope="row">${res.id}</th>
                  <td>${res.contractDate}</td>
                  <td>${res.minPriceRatio}</td>
                  <td>${res.contractCode}</td>
                </tr>`
            contractBody.innerHTML += template;
            alert('계약이 등록 되었습니다.')
            location.href = "index.html"
        },
        error: function(request, status, error) {
            console.log(request);
            console.log(status);
            console.log(error);
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}

function contractEnroll() {
    let contractDate = document.querySelector('.contract-1').value;
    let minPriceRatio = document.querySelector('.contract-2').value;
    let contractCode = document.querySelector('.contract-3').value;
    if (valueFilledCheck(contractDate, minPriceRatio, contractCode) === true) {
        sendContractData();
    }
}