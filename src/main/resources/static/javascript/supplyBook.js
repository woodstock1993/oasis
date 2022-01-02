function valueFilledCheck(a, b) {
    if( a==='' || b==='') {
        alert('빈칸 없이 입력해주세요.');
        return false;
    }
    return true;
}
function supplyAndBookEnroll() {
    let contractId = document.querySelector('.supplyAndBook-1').value;
    let bookId = document.querySelector('.supplyAndBook-2').value;

    if ((valueFilledCheck(contractId, bookId))) {
        getSupplyAndBookData(contractId, bookId);
    }
}

function getSupplyAndBookData(supplyId, bookId) {
    let success = 0

    $.ajax({
        type: "GET",
        url: `/contracts/${supplyId}`,
        contentType: "application/json",
        success: function (res) {
            console.log(res);
            success++;

            $.ajax({
                type: "GET",
                url: `/book/${bookId}`,
                contentType: "application/json",
                success: function (res) {
                    console.log(res);
                    success++;
                    if (success === 2) {
                        console.log("포스트요청 가동")

                        $.ajax({
                            type: "POST",
                            url: "/supplybook",
                            contentType: "application/json",
                            data: JSON.stringify({
                                Supply: `${supplyId}`,
                                Book: `${bookId}`,
                            }),
                            success: function (res) {
                                console.log(res);
                                alert('공급 및 도서 고유번호가 등록되었습니다.')
                                location.href = "supply_book.html"
                            },
                            error: function(request) {
                                alert("ERROR: "+request.status + "\n" + "POST ERROR 발생");
                            }
                        })
                    }
                },
                error: function(request) {
                    alert("ERROR: "+request.status + "\n" + "\n" +
                        "해당하는 도서고유번호"+`${contractId}`+"가 없습니다." + "\n" + "\n" +
                        "유효한 도서고유 번호를 입력하세요");
                }
            })
        },
        error: function(request) {
            alert("ERROR: "+request.status + "\n" + "\n" +
                "해당하는 공급계약번호"+`${contractId}`+"가 없습니다." + "\n" + "\n" +
                "유효한 공급계약 번호를 입력하세요");
        }
    })
}