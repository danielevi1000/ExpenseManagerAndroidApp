function typeChange() {
    var type = document.getElementById("type").value;
    if (type === "Income") {
        document.getElementById("category").disabled = true;
    } else
        document.getElementById("category").disabled = false;
}

function checkFields() {
    var type, category, details, amount, month;
    type = document.getElementById("type").value;
    category = document.getElementById("category").value;
    details = document.getElementById("details").value;
    amount = document.getElementById("amount").value;
    month = document.getElementById("month").value;

    if (type === "Expense" && (category == null || category === ""))
        return false;
    if (month == null || month === "")
        return false
    if (amount == null || amount === "")
        return false

    return true;
}

function getTransaction() {
    var type, category, details, amount, month;

    if (document.getElementById("type") != null)
        type = document.getElementById("type").value;
    if (document.getElementById("category") != null)
        category = document.getElementById("category").value;
    if (document.getElementById("details") != null)
        details = document.getElementById("details").value;
    if (document.getElementById("amount") != null)
        amount = document.getElementById("amount").value;
    if (document.getElementById("month") != null)
        month = document.getElementById("month").value;

    if (document.getElementById("type").value === "Income")
        category = "";

    if (checkFields(type, category, details, amount, month) == true) {
        var isInserted = window.vm.insert(type, category, details, amount, month);
        if (isInserted) {
            alertify.alert('Success', 'Transaction successfully inserted!', function () {
                location.reload();
            });
        }
    } else
        alertify.alert('Massage', 'Please fill all required fields');
}

function deleteAll() {
    var deletedRows = window.vm.deleteAllTransactions();
    if (deletedRows > 0) {
        alertify.alert('Success', 'Transactions successfully deleted!', function () {
            location.reload();
        });
    } else {
        alertify.alert('Massage', 'No transactions to delete');
    }
}

function deleteTransaction() {
    var transactionID = document.getElementById("transactionid").value;
    var deletedRows = window.vm.delete(transactionID);
    if (deletedRows > 0) {
        alertify.alert('Success', 'Transaction #' + transactionID + ' successfully deleted!', function () {
            location.reload();
        });
    } else {
        alertify.alert('Massage', 'Transaction does not exist!');
    }
}

