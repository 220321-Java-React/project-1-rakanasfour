const url = "http://localhost:3000"; //putting our base URL in a variable for cleaner code below

document.getElementById("addRequest").addEventListener("click", addRequestFunction);

async function addRequestFunction(){
    let rid = document.getElementById("reimb_id").value;
    let amount = document.getElementById("reimb_amount").value;
    let submitted = document.getElementById("reimb_submitted").value;
    let author_fk = document.getElementById("reimb_author_fk").value;
    let status_id_fk = document.getElementById("reimb_status_id_fk").value;
    let type_id_fk = document.getElementById("reimb_type_id_fk").value;

    let requestObject= {
        reimb_id:rid,
        reimb_amount:amount,
        reimb_submitted:submitted,
        user:author_fk,
        status : status_id_fk,
       type:type_id_fk
    }
    console.log(requestObject);



    let response = await fetch(url+"/requests", {

        method: "POST", //send a POST request (would be a GET if we didn't specify...)
        body: JSON.stringify(requestObject), //turning our user object into JSON to send to the server
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a session)
        //future fetches after login will require this "include" value 
    })
    
    //log the response status code, useful for debugs
    console.log(response.status);
    
    
    //control flow based on successful/unsuccessful login
    if(response.status === 202){
    
         //converting from json to JS
         let data = await response.json();
    
        //wipe our login row and welcome the user
    
        
    
        //THIS IS PROBABLY WHERE YOUR REDIRECT WOULD BE IF USING MULTIPLE HTML PAGES
        //don't be intimidated, it's an easy google :)
    
    } else {
        alert('something went wrong');

        //document.getElementById("welcomeHead").innerText="Login failed! Try Again";
        //document.getElementById("welcomeHead").style.color = "red";
    }

}