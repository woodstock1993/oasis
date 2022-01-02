$(document).ready(function () {
    getBooks();
})

function valueFilledCheck3(a, b, c, d, e, f, g, h) {
    if( a==='' || b==='' || c==='' || d==='' || e==='' || f==='' || g==='' || h==='') {
        alert('빈칸 없이 입력해주세요.');
        return false;
    }
    return true;
}



function getBooks() {
    let bookBody = document.querySelector('.books-body');
    bookBody.innerHTML = "";

    $.ajax({
        type: "GET",
        url: "/books",
        contentType: "application/json",
        success: function (res) {
            for(let i = 0; i < res.length; i++) {
                let template = `
                <div class="${res[i].id}-contract book-block">                    
                    <div class="contract-item ${res[i].id}-book_id">${res[i].id}</div>
                    <div class="contract-item ${res[i].id}-book_title">${res[i].bookTitle}</div>
                    <div class="contract-item ${res[i].id}-book_genre">${res[i].bookGenre}</div>
                    <div class="contract-item ${res[i].id}-book_quantity">${res[i].bookQuantity}</div>
                    <div class="contract-item ${res[i].id}-book_supply_price">${res[i].bookSupplyPrice}</div>
                    <div class="contract-item ${res[i].id}-author">${res[i].author}</div>
                    <div class="contract-item ${res[i].id}-book-published_date">${res[i].bookPublishedDate}</div>
                    <div class="contract-item ${res[i].id}-book_full_price">${res[i].bookFullPrice}</div>
                    <div class="contract-item ${res[i].id}-book-applied_rate">${res[i].appliedDcRate}</div>
                <div>                
                <button type="button" onclick ="openEditBook(${res[i].id})" class="btn btn-primary ${res[i].id}-contract-edit">수정</button>
                <button type="button" onclick ="deleteBook(${res[i].id})" class="btn btn-danger ${res[i].id}-contract-delete">삭제</button>                                                                                                           
                `
                bookBody.innerHTML += template;
            }
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "GET ERROR 발생");
        }
    })
}

function bookEnroll() {
    let bookTitle = document.querySelector('.book-1').value;
    let bookGenre = document.querySelector('.book-2').value;
    let bookQuantity = document.querySelector('.book-3').value;
    let bookSupplyPrice = document.querySelector('.book-4').value;
    let bookAuthor = document.querySelector('.book-5').value;
    let bookPublishedDate = document.querySelector('.book-6').value;
    let bookFullPrice = document.querySelector('.book-7').value;
    let appliedDcRate = document.querySelector('.book-8').value;
    if (valueFilledCheck3(bookTitle,bookGenre,bookQuantity,bookSupplyPrice,bookAuthor,bookPublishedDate,bookFullPrice,appliedDcRate)) {
        sendBookData();
    }
}

function sendBookData() {
    let bookTitle = document.querySelector('.book-1').value;
    let bookGenre = document.querySelector('.book-2').value;
    let bookQuantity = document.querySelector('.book-3').value;
    let bookSupplyPrice = document.querySelector('.book-4').value;
    let bookAuthor = document.querySelector('.book-5').value;
    let bookPublishedDate = document.querySelector('.book-6').value;
    let bookFullPrice = document.querySelector('.book-7').value;
    let appliedDcRate = document.querySelector('.book-8').value;

    $.ajax({
        type: "POST",
        url: "/books",
        contentType: "application/json",
        data: JSON.stringify({
            bookTitle: `${bookTitle}`,
            bookGenre: `${bookGenre}`,
            bookQuantity: `${bookQuantity}`,
            bookSupplyPrice: `${bookSupplyPrice}`,
            author: `${bookAuthor}`,
            bookPublishedDate: `${bookPublishedDate}`,
            bookFullPrice: `${bookFullPrice}`,
            appliedDcRate: `${appliedDcRate}`,
        }),
        success: function (res) {
            console.log(res);
            alert('도서가 등록되었습니다.')
            location.href = "books.html"
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
        }
    })
}

function editBook(id) {
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

function openEditBook(id) {
    let modal = document.querySelector('.edit-modal');
    let modalEditInput = document.querySelector('.modal-edit-input');

    let bookId = document.getElementsByClassName(`${id}-book_id`)[0].textContent;
    let bookTitle = document.getElementsByClassName(`${id}-book_title`)[0].textContent;
    let bookGenre = document.getElementsByClassName(`${id}-book_genre`)[0].textContent;
    let bookQuantity = document.getElementsByClassName(`${id}-book_quantity`)[0].textContent;
    let bookSupplyPrice = document.getElementsByClassName(`${id}-book_supply_price`)[0].textContent;
    let bookAuthor = document.getElementsByClassName(`${id}-author`)[0].textContent;
    let bookPublishedDate = document.getElementsByClassName(`${id}-book-published_date`)[0].textContent;
    let bookFullPrice = document.getElementsByClassName(`${id}-book_full_price`)[0].textContent;
    let appliedDcRate = document.getElementsByClassName(`${id}-book-applied_rate`)[0].textContent;


    modalEditInput.innerHTML =
        `<div class="contract-input-box">
            <div class="input-group">
                <span class="input-group-text">도서정보</span>
                <input type="text" aria-label="도서코드" placeholder="${c_company}" class="form-control i-contract-4">
                <input type="text" aria-label="도서제목" placeholder="${c_date}" class="form-control i-contract-1">
                <input type="text" aria-label="도서장르" placeholder="${c_ratio}" class="form-control i-contract-2">
                <input type="text" aria-label="도서수량" placeholder="${c_code}" class="form-control i-contract-3">
                <input type="text" aria-label="도서수량" placeholder="${c_code}" class="form-control i-contract-3">
                <input type="text" aria-label="도서수량" placeholder="${c_code}" class="form-control i-contract-3">
                <input type="text" aria-label="도서수량" placeholder="${c_code}" class="form-control i-contract-3">
                <input type="text" aria-label="도서수량" placeholder="${c_code}" class="form-control i-contract-3">
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

function deleteBook(id) {
    $.ajax({
        type: "DELETE",
        url: `/book/${id}`,
        success: function (response) {
            console.log(`id ${response} is deleted`);
            alert(`도서번호(${id}) 관련 정보가 삭제 되었습니다.`)
            location.href = "books.html";
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "해당 도서번호가 DB에 없습니다.");
        }
    })
}