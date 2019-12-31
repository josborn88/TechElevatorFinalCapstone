<template>
  <div id="login" class="text-center">
    <form class="form-block" @submit.prevent="login">
      <h1 class="form-title">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
        New User created, please sign in to comfirm credentials.
      </div>
      <div class="fm-data">
      <label for="username" class="fm-label">Username:</label>
      <input
        type="text"
        id="username"
        class="fm-txt"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div class="fm-data">
      <label for="password" class="fm-label">Password:</label>
      <input
        type="password"
        id="password"
        class="fm-txt"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <div class="btn-block">
      <button class="btn" type="submit">Sign in</button>
      </div>
     
    </form>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
        userRole: ''
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
           

            if (auth.getUser().rol === "fieldOps") {
              this.$router.push('/report');
            } else if (auth.getUser().rol === "systemAdmin") {
              this.$router.push('/register');
             } else {
              this.$router.push('/maintenanceView');
            }
            
          }
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style>

</style>
