
document.getElementById('editBioBtn').addEventListener('click', function() {
    document.getElementById('editBioBtn').style.display = 'none';
    document.getElementById('biographyForm').style.display = 'block';
    var currentURL = window.location.href;
    var representativeID = currentURL.substring(currentURL.lastIndexOf('/') + 1);
    var formAction = '/representative/' + representativeID + '/update-biography';
    document.getElementById('biographyForm').action = formAction;
});

document.getElementById('biographyForm').addEventListener('submit', function() {
    var currentURL = window.location.href;
    var representativeID = currentURL.substring(currentURL.lastIndexOf('/') + 1);
    var formAction = '/representative/' + representativeID + '/update-biography';
    document.getElementById('biographyForm').action = formAction;
});
