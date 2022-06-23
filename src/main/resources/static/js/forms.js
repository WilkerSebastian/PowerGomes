const img = document.querySelector("#img-forms")

function upImg() {

    setTimeout(() => {

        const inputImg = document.querySelector("#imagem").value

        console.log(inputImg);

        if (inputImg == "") {

            img.style.display = "none"

        } else {

            img.style.display = "block"
            img.src = inputImg

        }

        window.requestAnimationFrame(upImg)

    }, 10)

}

upImg()