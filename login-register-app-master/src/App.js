import React, { useState } from "react";
import {RadioGroup, Radio} from "react-radio-group"
import "./App.css";
import { useSpring, animated } from "react-spring";

function App() {

  const [file_path, setFilePath] = useState("343");
  const [param, setParam] = useState("");


  const [registrationFormStatus, setRegistartionFormStatus] = useState(false);
  const loginProps = useSpring({ 
    left: registrationFormStatus ? -500 : 0, // Login form sliding positions
  });
  const registerProps = useSpring({
    left: registrationFormStatus ? 0 : 500, // Register form sliding positions 
  });

  const loginBtnProps = useSpring({
    borderBottom: registrationFormStatus 
      ? "solid 0px transparent"
      : "solid 2px #1059FF",  //Animate bottom border of login button
  });
  const registerBtnProps = useSpring({
    borderBottom: registrationFormStatus
      ? "solid 2px #1059FF"
      : "solid 0px transparent", //Animate bottom border of register button
  });

  function registerClicked() {
    setRegistartionFormStatus(true);
  }
  function loginClicked() {
    setRegistartionFormStatus(false);
  }

  return (
    <div>
  <div>
    <h1 style={registerBtnProps}>Hello</h1>
  </div>
  <div className="login-register-wrapper">
      <div className="nav-buttons">
        <animated.button
          onClick={loginClicked}
          id="loginBtn"
          style={loginBtnProps}
        >
        </animated.button>
      </div>
      <div className="form-group">
        <animated.form action="" id="loginform" style={loginProps}>
        <h1>PDF generator</h1>
          <LoginForm />
        </animated.form>
      </div>
    </div>
    </div>
  );


function LoginForm() {
  return (
    
    <React.Fragment>
      <h2 style={{padding:5}}>Select language...</h2>
      <RadioGroup name="fruit" style={{ display: 'flex' , width: 'auto', marginLeft:130}}>
        <Radio value={16} style={{ width: 'auto' }}/><h3 style={{padding:5}} onChange={setParam(16)}>16</h3>
        <Radio value={32} style={{ width: 'auto' }}/><h3 style={{padding:5}} onClick={setParam(32)}>32</h3>
        <Radio value={64} style={{ width: 'auto' }}/><h3 style={{padding:5}} onClick={setParam(64)}>64</h3>
      </RadioGroup>
      <h2 style={{padding:5}}>Select one of the three forms...</h2>
      <div style={{display:'flex', padding:2} } >

      <input type="button" value="submit" style={{ padding:12}}  className="submit" onClick={()=>{
        console.log(file_path)
        console.log(param)
      }}/>

      <input type="button" value="submit" style={{padding:12}} className="submit" onClick={()=>{
        console.log(file_path)
        console.log(param)
      }}/>

      <input type="button" value="submit" style={{padding:12}} className="submit" onClick={()=>{
        console.log(file_path)
        console.log(param)
      }}/>
      </div>
    </React.Fragment>
  );
}

function InputFile(){
  return (
    <div>
      <h4 style={{padding:5}}>Select your image to be compressed</h4>
      <input style={{paddingTop: 6}} type='file' id="image" onChange={
        (e)=>{
        setFilePath(e.target.value)
        console.log(e.target.value)
        console.log(file_path)
      }}/>
      <table style={{marginLeft:120}}>
        <tr>
          <tc>Image size:
            <h2 value={file_path}/>
          </tc>
          <tc>123</tc>
        </tr>
        <tr>
          <tc>Image dimensions:</tc>
          <tc>123</tc>
        </tr>
        <tr>
          <tc>Image format:</tc>
          <tc>123</tc>
        </tr>
      </table>
    </div>
  )
}

function RegisterForm() {
  return (
    <React.Fragment>
      <h2 style={{padding:5}}>Select number of Clusters </h2>
      <h3 style={{paddingLeft:5}}>Color Bit Rate</h3>
      <RadioGroup name="fruit" style={{ display: 'flex' , width: 'auto', marginLeft:130}}>
        <Radio value={16} style={{ width: 'auto' }}/><h3 style={{padding:5}} onChange={setParam(16)}>16</h3>
        <Radio value={32} style={{ width: 'auto' }}/><h3 style={{padding:5}} onClick={setParam(32)}>32</h3>
        <Radio value={64} style={{ width: 'auto' }}/><h3 style={{padding:5}} onClick={setParam(64)}>64</h3>
      </RadioGroup>
      <input  value="send" className="submit" />
      <input  value="result" className="submit" style={{marginLeft:5}}/>
    </React.Fragment>
  );
}
}

export default App;
