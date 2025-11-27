

import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
function eatclub(){

    useEffect(()=>{
        loaddata();
    },[])


    const [data , setData] = useState("null");

    const loaddata = async()=>{
            
        
        try{
            const mydata = await axios.get("http://localhost:8080/health-check");
            setData(mydata.data);
        }catch(exception){
            console.log(exception);
        }

    }

    console.log(data);

    return(
        <>
        <h1>Hello From eatclub component</h1> 
        <p>heath Check = + {data}</p>
        </>
    )
}



export default eatclub;