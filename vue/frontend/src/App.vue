<template>
  <div id="app" class="wrapper">
    <div class="logo">
      <img src="./assets/fidelis.png">
      <h3>Risk Management System</h3>
    </div>
    <div class="nav">
      <ul>
        <li>
         <router-link to="/">Home</router-link>
        </li>
      </ul>
      
      <div class="flex">
      <ul>
        <li>
           <router-link to="/report">Report Risk</router-link>
        </li>
      </ul>

      <ul>
        <li>
           <router-link to="/maintenanceView">View Reports</router-link>
        </li>
      </ul>

      <ul>
        <li>
           <router-link to="/register">Create User</router-link>
        </li>
      </ul>

      <ul>
        <li>
           <router-link to="/changePassword">Change Password</router-link>
        </li>
      </ul>
      </div>

        <ul>
        <li>
          <!-- <router-link to="/login" v-on:click.prevent="logout()">Logout</router-link> -->
          <span><button class="logout-btn" v-on:click.prevent="logout()">Log Out</button></span>
        </li>
      </ul>
      
    </div>
    <router-view/>
  </div>
</template>

<script>
import auth from './auth'

export default {

  
  methods: {
    logout() {
      auth.logout();
      this.$router.push('/login');
    }
  }
}
</script>


<style>
* {
	padding: 0;
	margin: 0;
}

.flex {
  display: flex;
}

.logo {
  display: flex;
  font-size: 2em;
  padding: 10px 0;
  margin-top: 20px;
}

.logo img {
  height: auto;
  max-height: 50px;
  width: auto;
  max-width: 100%;
  padding-bottom: none;
  padding: 0 5px 0 0;
  border-right: solid 2px #333;
  margin-right: 10px;
}

.logo h3 {
  position: relative;
  margin-top: 20px;
}

.wrapper {
  width: 90%;
  max-width: 1600px;
  margin: 0 auto;
}

body {
  font-family: roboto, sans-serif;
	font-size: 10px;
  background-color: #E5E2E1;
}

.nav {
	display: flex;
  background-color: #AE313C;
  justify-content: space-between;
}

.nav li {
	list-style: none;
	position: relative;
}

.nav a {
	display: block;
	color: #fff;
	padding: 10px 15px;
	border-right: solid 1px #E5E2E1;
  border-left: solid 1px #E5E2E1;
	text-decoration: none;
	transition: background-color .3s;
	font-size: 1.6em;
}

.nav a:hover {
	color: #AE313C;
	background-color: #fff;
  transition: background-color .3s;
}

.form-block {
  padding: 30px 0;
  margin-right: 10%;
}

.form-title, .page-title {
  padding: 10px 0;
  font-size: 2.0em;
}

.fm-data {
  display: flex;
  width: 105%;
  justify-content: space-between;
  padding: 5px 0;
  align-items: center;
}

.fm-label {
  position: relative;
  font-size: 1.6em;
}

.fm-txt {
  width: 325px;
  height: 30px;
  text-align: left;
  font-size: 1.5em;
}

.report {
  width: 400px;
}

/* left off */
.risk-block:lastChild {
  align-content: none;
}

.fm-txt.summary {
  width: 400px;
  height: 250px;
}

.text-center {
    display: flex;
    text-align: left;
    justify-content: center;
}

.text-center h1 {
    text-align: center;
}

.btn-block {
  text-align: center;
  justify-content: center;
}

.btn {
    border-radius: 5px;
    font-size: 1.6em;
    padding: 10px;
    margin: 10px 0;
    position: relative;
    display: inline-block;
    background-color: #333;
    color: #fff;
}

.btn:hover {
	color: #333;
	background-color: #fff;
	transition: background-color .3s;
}

.login-link, .register-link {
  text-align: center;
  justify-content: center;
  font-size: 1.5em;
  padding: 5px 0;
}
.logout-btn {
  display: block;
  background-color: #AE313C;
  color: #fff;
	padding: 10px 15px;
	border-right: solid 1px #E5E2E1;
	text-decoration: none;
	transition: background-color .3s;
	font-size: 1.6em;
  text-align: right;
}
.logout-btn:hover {
	color: #AE313C;
	background-color: #fff;
  transition: background-color .3s;
}


</style>
