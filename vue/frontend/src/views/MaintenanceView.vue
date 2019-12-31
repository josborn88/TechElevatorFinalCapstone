<template>
  <div id="maintenance">
      <h1 class="page-title">View Reports</h1>
        <!-- <button v-on:click.prevent="displayReports()">View Reports</button> -->
        <view-reports :apiURL="API_URL"/>
  </div>
</template>

<script>
import viewReports from '../components/viewReports.vue'

export default {
    components: {
        viewReports
    },
    name: 'maintenance',
    data() {
        return {
            //API_URL: "http://5deebc6fb3d17b00146a2d76.mockapi.io/api/report",
            API_URL: "http://localhost:8080/AuthenticationApplication/api",

        };
    },
    methods: {
        displayReports() {
           this.getAllReports();
           
         }
    }
}
</script>

<style scoped>
    .page-title {
        font-size: 2em;
        margin-top: 20px;
        padding: 10px 0;
        text-align: center;
        justify-content: center;
    }


</style>