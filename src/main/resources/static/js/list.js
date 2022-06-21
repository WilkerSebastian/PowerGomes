const textLista = document.getElementsByClassName('text-lista')

for (let index = 0; index < textLista.length; index++) {

    const element = textLista[index];

    if(element.innerHTML.length > 100){

        element.innerHTML = element.innerHTML.substring(0 , 100) + "..."
    
    }
    
}