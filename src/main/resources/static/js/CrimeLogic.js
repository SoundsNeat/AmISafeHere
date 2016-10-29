var $results = document.querySelector('#addressIsHere');
var appendToResult = $results.insertAdjacentHTML.bind($results, 'afterend');
TeleportAutocomplete.init('#citySearch').on('change', function(value) {
appendToResult('<pre id="selectedCity" style="display:none;">' + value.title + '</pre>');
});

function reformatAddress(address) {
    return address.replace(/, /g,"-").replace(/ /g,"_");
}

function loadModal() {
    $('#resultModal').modal({show:true});
}

function parseServerConnectError() {
    $('#serverError').show();
    $('#noGpsError').hide();
    $('#resultModalBody').hide();

}

function parseNoGpsError() {
    $('#noGpsError').show();
    $('#resultModalBody').hide();
    $('#serverError').hide();
}

function parseOutput(result) {
    $('#resultModalBody').show();
    $('#noGpsError').hide();
    $('#serverError').hide();
    if (result.result) {
        $('#displayResult').show();
        $('#noResultError').hide();
    } else {
        $('#noResultError').show();
        $('#displayResult').hide();
    }
}
