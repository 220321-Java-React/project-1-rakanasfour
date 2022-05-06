const url = "http://localhost:3000"; //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in fetch requests and make calls to our server by appending endpoints

//add an event listener to give our buttons functionality (using DOM selection)
//"When the getEmployeeButton gets clicked, execute the getEmployees function"
//document.getElementById("getRequestButton").addEventListener("click", getRequests);

//"When the loginButton gets clicked, execute the loginFunction"
document.getElementById("loginButton").addEventListener("click", loginFunction);





//this function will send the user-inputted login credentials to our server
async function loginFunction(){

//gather the user inputs from the login inputs
//when the login button is clicked, the value from username and password will be put into variables
let usern = document.getElementById("username").value;
let userp = document.getElementById("password").value;




//we want to send the user/pass as JSON, so we need a JS object first.
let user = {
    username:usern,
    password:userp
}
//This object should reflect the LoginDTO in our Java... This is the data we want to transfer

//for debugging purposes, print out the user object to the console
console.log(user);

//fetch request to the server
//rememeber, the second parameter in a fetch is for configuring our fetch request
//fetch sends a GET by default, but we need a POST, as well as some other configs
let response = await fetch(url+"/login", {

    method: "POST", //send a POST request (would be a GET if we didn't specify...)
    body: JSON.stringify(user), //turning our user object into JSON to send to the server
    credentials: "include"
    //this last line will ensure that the cookie is captured (so that we can have a session)
    //future fetches after login will require this "include" value 
})

//log the response status code, useful for debugs
console.log(response.status);


//control flow based on successful/unsuccessful login
if(response.status === 202 ){


     //converting from json to JS
     let data = await response.json();
     console.log(data);
     if (data.ers_username ==="Manager"){ 
     window.location.replace("/Manager.html");
    }else{    window.location.replace("/Employee.html");


    };




    

    //THIS IS PROBABLY WHERE YOUR REDIRECT WOULD BE IF USING MULTIPLE HTML PAGES
    //don't be intimidated, it's an easy google :)

} else {
    document.getElementById("welcomeHead").innerText="Login failed! Try again!";
    document.getElementById("welcomeHead").style.color = "red";
}


}