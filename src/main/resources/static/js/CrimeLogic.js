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
    $('#cityStateTitle').hide();

}

function parseNoGpsError() {
    $('#noGpsError').show();
    $('#cityStateTitle').hide();
    $('#serverError').hide();
}

function parseOutput(result) {
    $('#cityStateTitle').show();
    $('#noGpsError').hide();
    $('#serverError').hide();
    if (result.result) {
        $('#displayResult').show();
        $('#noResultError').hide();
        constructStars(result.index);
    } else {
        $('#noResultError').show();
        $('#displayResult').hide();
    }
}

function constructStars(value) {
    var imgTag = "<img src=\"../img/star-small-$Val.png\" alt=\"\" />";

    var index = 0;
    var htmlText = "";
    while (index < 5) {
        htmlText += imgTag.replace("$Val", index < value ? "1" : "0");
        index++;
    }
    $('#resultStars').html(htmlText);
}
