<template>
  <div id="register" class="text-center">
    <form class="form-block" @submit.prevent="changePassword">
      <h1 class="form-title">Change Password</h1>
      <div class="alert alert-danger" role="alert" v-if="changePasswordErrors">
        There were problems changing your password.
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
      <div class="fm-data">
      <label for="confirmPassword" class="fm-label">Confirm Password:</label>
      <input
        type="password"
        id="confirmPassword"
        class="fm-txt"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
      <div class="fm-data">
         <label for="newPassword" class="fm-label">New Password:</label>
      <input
        type="password"
        id="newPassword"
        class="fm-txt"
        placeholder="New Password"
        v-model="user.newPassword"
        required
      />
      </div>
      <div class="btn-block">
      <button class="btn" type="submit">
        Change Password
      </button>
      </div>
      <div class="login-link">
      <router-link :to="{ name: 'login' }">
        Go to login.
      </router-link>
      </div>
    </form>
  </div>
</template>

<script>
import auth from '../auth'

export default {
  name: 'changePassword',
  data() {
    return {
      user: {
        username: auth.getUser().sub, 
        password: '',
        confirmPassword: '',
        newPassword: '', 
      },
      changePasswordErrors: false,
    };
  },
  methods: {
    changePassword() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/changePassword`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + auth.getToken(),
        },
        body: JSON.stringify(this.user), 
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/login', query: { registration: 'success' } });
          } else {
            this.changePasswordErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style>
</style>
