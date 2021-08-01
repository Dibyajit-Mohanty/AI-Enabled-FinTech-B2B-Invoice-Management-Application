// // Add invoice

// // Get the modal
// var modal = document.getElementById("modaladd");


// // Get the button that opens the modal
// var btn = document.getElementById("add-btn");



// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[0];

// // When the user clicks the button, open the modal 
// btn.onclick = function() {
//     modal.style.display = "block";
// }




// // When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//         modal.style.display = "none";
//     }
//     // When the user clicks anywhere outside of the modal, close it
// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// }

// // Edit invoice

// // Get the modal
// var modaledit = document.getElementById("modaledit");


// // Get the button that opens the modal
// var editbtn = document.getElementById("edit-btn");



// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[1];

// // When the user clicks the button, open the modal 
// editbtn.onclick = function() {
//     modaledit.style.display = "block";
// }




// // When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//         modaledit.style.display = "none";
//     }
//     // When the user clicks anywhere outside of the modal, close it
// window.onclick = function(event) {
//     if (event.target == modaledit) {
//         modaledit.style.display = "none";
//     }
// }

// // Delete invoice

// // Get the modal
// var modaldelete = document.getElementById("modaldelete");


// // Get the button that opens the modal
// var deletebtn = document.getElementById("delete-btn");



// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[2];

// // When the user clicks the button, open the modal 
// deletebtn.onclick = function() {
//     modaldelete.style.display = "block";
// }




// // When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//         modaldelete.style.display = "none";
//     }
//     // When the user clicks anywhere outside of the modal, close it
// window.onclick = function(event) {
//     if (event.target == modaldelete) {
//         modaldelete.style.display = "none";
//     }
// }

var c = 0;

function makeAvailable(chkBox) {
    var boxes = document.getElementById('mark');
    if (chkBox.checked) {
        c++;
        document.getElementById('delete-btn').disabled = 0
        document.getElementById('edit-btn').disabled = false
        document.getElementById('delete-btn').style.border = '1px solid #14AFF1'
        document.getElementById('edit-btn').style.border = '1px solid #14AFF1'
        if (c >= 2) {
            c = 0;
            document.getElementById('edit-btn').disabled = true
            document.getElementById('edit-btn').style.border = '1px solid #97A1A9'
        }
        // document.get
    } else {
        var chk = false;
        let c1 = 0;
        for (let i = 0; i < mark.length; i++) {
            if (mark[i].checked) {
                chk = true;
                c1++;
            }
        }
        if (chk == false) {
            document.getElementById('delete-btn').disabled = 1
            document.getElementById('edit-btn').disabled = true
            document.getElementById('delete-btn').style.border = '1px solid #97A1A9'
            document.getElementById('edit-btn').style.border = '1px solid #97A1A9'
        }
        if (c1 == 1) {
            document.getElementById('edit-btn').disabled = false
            document.getElementById('edit-btn').style.border = '1px solid #14AFF1'
        }
    }
}
//--------------------------------------------------- 137
// Add invoice

// Get the modal
var modal = document.getElementById("modaladd");


// Get the button that opens the modal
var btn = document.getElementById("add-btn");



// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}




// When the user clicks on <span> (x), close the modal
span.onclick = function() {
        modal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



// Delete invoice

// Get the modal
var modaldelete = document.getElementById("modaldelete");


// Get the button that opens the modal
var deletebtn = document.getElementById("delete-btn");



// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[2];

// When the user clicks the button, open the modal 
deletebtn.onclick = function() {
    modaldelete.style.display = "block";
}




// When the user clicks on <span> (x), close the modal
span.onclick = function() {
        modaldelete.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
        if (event.target == modaldelete) {
            modaldelete.style.display = "none";
        }
    }
    //--------------------------------------------------------------- 236
    // //to show the edit modal
    // $('#editBtn').click(function(){
    //   $('#myModalEdit').toggle('block')
    // })


//to handle the delete functionality
$('#saved').click(function(event) {

  var data = []
  $('table #mark:checked').each(function() {
      var rowvalue = $(this).closest('tr')
      let r = rowvalue.find('td:eq(1)').text()
      data.push(r)

  })
  console.log(JSON.stringify(data));
  data = JSON.stringify(data)

  $.ajax({
          'url': 'http://localhost:8080/H2HBABBA1625/update',
          'type': 'DELETE',
          'contentType': 'application/json',
          'data': data
      })
      .then(res => {
          $('#delete-btn').prop('disabled', true);
          alert("deleted successfully")
          $('#modaldelete').toggle('none')
          $('table tbody').empty()
          getData(page)
      })

})

//to handel the cancel event
$('#canceld').click(() => {
  $('#modaldelete').toggle('none')
})





//to edit the data
$('#edit-btn').click((event) => {
    console.log('in');
    $('table #mark:checked').each(function() {
        var rowvalue = $(this).closest('tr')
        let k = rowvalue.find('td:eq(1)').text();
        let r = rowvalue.find('td:eq(5)').text()
        let r1 = rowvalue.find('td:eq(8)').text();
        console.log(r);
        $('#key').val(k)
        $('#invoiceamt').val(r)
        $('#enote').val(r1)
        $('#modaledit').toggle('block')
    })
})

$('#savee').click(() => {
    let data = {
        key: $('#key').val(),
        amount: $('#invoiceamt').val(),
        note: $('#enote').val()
    }
    console.log(data)
    $.ajax({
            'url': 'http://localhost:8080/H2HBABBA1625/update',
            'type': 'POST',
            'data': data,
            'datatype': 'json'
        })
        .then(data => {
            $('table tbody').empty()
            $('#modaledit').toggle('none')
            getData()
        })
})

//function for getting the data from the server
function getData(page) {
    $('table tbody').empty()
    $.ajax({
        'url': 'http://localhost:8080/H2HBABBA1625/dummyServlet',
        'type': 'GET',
        'data': { page: page },
        'datatype': 'json'
    }).then(data => {
        data = JSON.parse(data)
        var tr = $('table tbody')
        data.map(item => {
            markup = `<tr id="trow">
        				<td class='row-value'><input type="checkbox" name="checkbox" id="mark"
                        onclick="makeAvailable(this)"></td>
        						<td hidden>${item.key}</td>
        						<td class="row-value">${item.name_customer}</td>
        						<td class="row-value">${item.cust_number}</td>
        						<td class="row-value">${item.invoice_id}</td>
        						<td class="row-value">${item.total_open_amount}</td>
        						<td class="row-value">${item.clear_date}</td>
        						<td class="row-value">${item.due_in_date}</td>
        						<td class="row-value">${item.note}</td>
        					</tr>`
            tr.append(markup)
        })
    })
}

$(document).ready(function() {
    getData(1)
})


//pagination
var page = 1;
$('#rightBtn').click(() => {
    page = page + 1;
    $('table tbody').empty();
    getData(page);
    console.log(page)
})

$('#leftBtn').click(() => {
    console.log(page)
    if (page == 1) {
        alert('You are in the home page')
    } else {
        page = page - 1;
        $('table tbody').empty()
        getData(page)
    }
})