
async function uploadToServer (formObj) {

    console.log("upload to server......")
    console.log(formObj)

    const response = await axios({
        method: 'post',
        url: '/user/upload',
        data: formObj,
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
        console.log(response.data)
    return response.data
}

async function removeFileToServer(fileName){

    const response = await axios.delete(`/user/remove/${fileName}`)

    return response.data

}