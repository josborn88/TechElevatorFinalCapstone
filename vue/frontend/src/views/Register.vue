<template>
  <div id="register" class="text-center">
    <form class="form-block" @submit.prevent="register">
      <h1 class="form-title">Create a New Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
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
      <label for="username" class="fm-label">User Role:</label>
  
      <select v-model="user.role" required autofocus>
          <option value='fieldOps'>Field Operative</option>
          <option value='maintenance'>Maintenance Personal</option>
          <option value="departmentAdmin">Department Admin</option>
          <option value="executiveManager">Executive Manager</option>
          <option value="fieldOpsSupervisor">Field Ops Supervisor</option>
          <option value="systemAdmin">System Admin</option>
      </select>





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
      <label for="password" class="fm-label">Confirm Password:</label>
      <input
        type="password"
        id="confirmPassword"
        class="fm-txt"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
      <div class="btn-block">
      <button class="btn" type="submit">
        Create Account
      </button>
      </div>
   
    </form>
  </div>
</template>

<script>
import auth from '../auth'

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
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
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
  created() {
        if (auth.getUser().rol !== "systemAdmin") {
            this.$router.push('/maintenanceView');
        }
   }
};
</script>

<style>
</style>
