let data;
function uploadExcel() {
    console.log('in function');
    var formData = new FormData();
    var fileInput = document.getElementById('excelFile');
    formData.append('file', fileInput.files[0]);
    this.data=formData;
    fetch('/excelupload', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            // Handle success
            console.log('Excel file uploaded successfully');
        } else {
            // Handle error
            console.error('Failed to upload Excel file');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}