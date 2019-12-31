<template>

  <div class="wrapper">
      <div class="rp-table-block" v-if="!(userRole == 'maintenance')">
        <table>
            <thead>
                <tr>
                <th>Complete</th>
                <th>Incomplete</th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-data">
                <td v-if="completePercent == '' "> - </td>
                <td v-else>{{ completePercent }}</td>
                <td v-if="incompletePercent == '' "> - </td>
                <td v-else>{{ incompletePercent }}</td>
                </tr>
            </tbody>
         </table>
        </div>
      <div class="rp-search-block flex-center">
      <div class="select-block" v-if="userRole != 'maintenance'">
      <div><label>Priority:</label></div>
      <select v-model="priorityFilter">
          <option value='View All'>View All</option>
          <option value=''>Not Assigned</option>
          <option value="High">High</option>
          <option value="Medium">Medium</option>
          <option value="Low">Low</option>
      </select>
      </div>
      
      <div class="select-block">
      <div><label>Repair Status:</label></div>
      <select v-model="repairedFilter">
          <option value='View All'>View All</option>
          <option value=0>Not Repaired</option>
          <option value=1>Repaired</option>
      </select>
      </div>
      
      <div class="select-block">
      <div><label>Client name:</label></div>
      <select v-model="clientFilter">
          <option value='View All'>View All</option>
          <!-- Use with Mock API -->
          <!-- <option v-for="report in reports" :key="report.id" :value="report.clientName">{{report.clientName}}</option> -->

            <!-- Use with real API -->
           <option v-for="clientName in clientNames" :key="clientName" :value="clientName">{{clientName}}</option>
          
      </select>
      </div>

      <div class="select-block">
      <div><label>Address:</label></div>
      <select v-model="addressFilter">
          <option value='View All'>View All</option>
          <!-- Use with Mock API
          <option v-for="report in reports" :key="report.id" :value="report.locationAddress">{{report.locationAddress}}</option> -->

          <!-- Use with real API -->
          <option v-for="address in clientAddress" :key="address" :value="address">{{address}}</option>
     
      </select>
      </div>
      </div>

      <div class="report-list" v-for="report in filteredList" :key="report.id">
        <div class="rp-info-block">

        <div class="rp-image">
            <!-- this is for database -->
            <img :src="require('../assets/reportImages/' + report.image.imageName)" />

            <!-- this is for mock api -->
            <!-- <img :src="report.imageName" /> -->

        </div>

        <div class="rp-detail">

        <div class="rp-priority">
            <div class="flex-center">
            <div class="report-cat">
            Priority:
            </div>
            <div class="alert" v-if="report.priority === 'High'"><img src="../assets/red-alert.png"></div>
            <div class="alert" v-else-if="report.priority === 'Medium'"><img src="../assets/orange-alert.png"></div>
            <div class="alert" v-else-if="report.priority === 'Low'"><img src="../assets/yellow-alert.png"></div>
            <div class="report-cat"> {{ report.priority != null ? report.priority : 'None Assigned' }} </div>
            </div>

            <div class="priority-switch">
            <div class="report-cat">
            Change Priority:
            </div>
            <div class="alert"><img v-on:click.prevent="updatePriority(report, 'High')" src="../assets/red-alert.png"></div>
            <div class="alert"><img v-on:click.prevent="updatePriority(report, 'Medium')" src="../assets/orange-alert.png"></div> 
            <div class="alert"><img v-on:click.prevent="updatePriority(report, 'Low')" src="../assets/yellow-alert.png"></div>
            </div>
        </div>

        <div class="rp-single">
            Reported by: {{ report.riskReporter }}
        </div>

        <div class="rp-single">
           Risk Name: {{report.name}}
        </div> 

        <div class="rp-single">
            <!-- Mock API -->
            <!-- Client Name: {{report.clientName}} -->
            <!-- real api -->
            Client Name: {{report.location.clientName}}
        </div>

        <div class="rp-single">
            <!-- mock API
            Address: {{report.locationAddress}} -->
            <!-- real api -->
            Address: {{report.location.address}}
        </div>
        
        <div class="rp-single">
            <!-- mock api
           Location of risk: {{report.locationName}} -->
            <!-- real api -->
           Location of risk: {{report.location.locationName}}
        </div>

        <div class="rp-single">
        <button class="show-btn" v-on:click="showSummary? showSummary = false : showSummary = true">
            <div v-if="showSummary === false">Show Summary</div>
            <div v-else-if="showSummary">Hide Summary</div>
        </button>
        <button class="show-btn" v-on:click="showForm? showForm = false : showForm = true">
            <div v-if="showForm === false">Show Form</div>
            <div v-else-if="showForm">Hide Form</div>
        </button>
        </div>
        <div class="rp-single summary" v-show="showSummary">
            Summary: {{ report.summary }}
        </div>

        
        <div class="rp-single flex-center" v-show="showForm">
            <div>
                <div class="flex-center">
                    <div class="alert" v-if="report.duplicate">
                        <img src="../assets/duplicate.png" />
                    </div> 
                    <div>
                        <label class="label">
                            {{ report.duplicate == true ? 'Duplicate Report' : 'Unique Report' }}
                        </label>
                    </div>
                </div>
                <button class="form-btn" v-on:click.prevent="updateIsDuplicate(report)" >
                    <div>Change Duplicate</div>
                    <div>Status</div>
                </button>
            </div>
            
            <div>
                <div class="flex-center">
                    <div>
                        <div class="label">
                            Repair Status:
                        </div>
                        <div class="alert" v-if="report.repaired">
                            <img src="../assets/complete.png" />
                            <label class="label">
                            {{ report.repaired == true ? 'Completed' : 'Awaiting Repair' }}
                            </label>   
                        </div>
                    </div>
                </div>
                    <button class="form-btn" v-on:click.prevent="updateRepairedStatus(report)">
                        <div>Change Repair</div> 
                        <div>Status</div>
                    </button> 
            </div>
                <div>
                    <form>
                        <div>
                        <label class="label"><div>Expected</div><div>Completion date:</div></label>
                        </div>
                        <div class="repair-block">
                        <input class="date-input" type="date" name="completion-date" v-model="report.repairDate">
                        <button class="submit" v-on:click.prevent="updateRepairDate(report)">Update</button>
                        <!-- <input class="submit" type="submit"> -->
                        </div>
                    </form>
                </div>
       </div>
    </div>   
  </div>
  </div>
  </div>
</template>

<script>
import auth from '../auth'

export default {
    props: {
        apiURL: String
    },
    name: "view-reports",
    data() {
        return {
            reports: [],
            priorityFilter: 'View All',
            repairedFilter: 'View All',
            clientFilter: 'View All',
            addressFilter: 'View All',
            userRole: auth.getUser().rol,
            completePercent: '',
            incompletePercent: '',
            showSummary: false,
            showForm: false,
        };
    },
    computed: {
        filteredList(vm) {
            let filteredLst = vm.reports;
            if(vm.priorityFilter != 'View All') {
                if(vm.priorityFilter === '') {
                    filteredLst = filteredLst.filter(report => report.priority === null);
                }
                else {
                    filteredLst = filteredLst.filter(report => report.priority === vm.priorityFilter);
                }
            }

            if(vm.repairedFilter != 'View All') {
                filteredLst = filteredLst.filter(report => report.repaired == vm.repairedFilter);
            }

            if(vm.clientFilter != 'View All') {
                // mock API
                // filteredLst = filteredLst.filter(report => report.clientName === vm.clientFilter);
                // Real API
                filteredLst = filteredLst.filter(report => report.location.clientName === vm.clientFilter);
            }

            if(vm.addressFilter != 'View All') {
                // mock API
                // filteredLst = filteredLst.filter(report => report.locationAddress === vm.addressFilter);
                // real API
                filteredLst = filteredLst.filter(report => report.location.address === vm.addressFilter);
            }

            vm.completePercent = this.avgCompleted(filteredLst);
            vm.incompletePercent = this.avgIncompleted(filteredLst);
            return filteredLst;
        },
        clientNames(vm){
            let clientNames = [...new Set(vm.reports.map(report => report.location.clientName))];
            return clientNames;
        },
        clientAddress(vm){
            let clientAddress = [...new Set(vm.reports.map(report => report.location.address))];
            return clientAddress;
        },
    },
    
    methods: {
        avgCompleted(filteredLst) {
            let sum = filteredLst.reduce((currentSum, report) => {
                return currentSum + report.repaired;
            }, null);

            return (sum); 
            // ((sum / filteredLst.length) * 100).toFixed(2);  --> this will change the hard number to a percentage if you want to view percent repaired
        },

        avgIncompleted(filteredLst) {
            let sum = filteredLst.reduce((currentSum, report) => {
                return currentSum + !report.repaired;
            }, null);
            return  (sum); 
            //((sum / filteredLst.length) * 100).toFixed(2); --> --> this will change the hard number to a percentage if you want to view percent not repaired
        },

        getAllReports() {
           fetch(this.apiURL, {
               headers: {
                   'Authorization': 'Bearer ' + auth.getToken(), 
               }
           })
            .then( response => {
                return response.json()
            })
            .then( data => {
                this.reports = data
            })
            .catch( err => console.error(err) );
        },
        updatePriority(report, newPriority) {
            report.priority = newPriority;
            fetch(`${this.apiURL}/updatePriority`,
                {
                    method: 'PUT',
                    headers: {
                         "Content-Type": "application/json",
                         'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: JSON.stringify(report)

                })
                .then((response) => {
                    if(response.ok) {
                        console.log(report.priority);
                    } else {
                        this.reportErrors = true;
                    }
                })
                .catch((err) => console.error(err));
        },
        updateRepairedStatus(report) {
            if (!report.repaired) {
                report.repaired = true;
                report.repairConfirmedBy = prompt("Who is confirming this repair?");
            } else {
                report.repaired = false;
                report.repairConfirmedBy = '';
            }
            fetch(`${this.apiURL}/updateIsRepaired`,
                {
                    method: 'PUT',
                    headers: {
                         "Content-Type": "application/json",
                        'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: JSON.stringify(report)

                })
                .then((response) => {
                    if(response.ok) {
                        console.log(report.repaired);
                    } else {
                        this.reportErrors = true;
                    }
                })
                .catch((err) => console.error(err));
        },
        updateIsDuplicate(report) {
            if (!report.duplicate) {
                report.duplicate = true;
                
            } else {
                report.duplicate = false;

            }
            fetch(`${this.apiURL}/updateDuplicate`,
                {
                    method: 'PUT',
                    headers: {
                         "Content-Type": "application/json",
                         'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: JSON.stringify(report)

                })
                .then((response) => {
                    if(response.ok) {
                        console.log(report.repaired);
                    } else {
                        this.reportErrors = true;
                    }
                })
                .catch((err) => console.error(err));
        },
        updateRepairDate(report) {
           
            fetch(`${this.apiURL}/updateRepairDate`,
                {
                    method: 'PUT',
                    headers: {
                         "Content-Type": "application/json",
                         'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: JSON.stringify(report)

                })
                .then((response) => {
                    if(response.ok) {
                        console.log(report.repairDate);
                    } else {
                        this.reportErrors = true;
                    }
                })
                .catch((err) => console.error(err));
        },
        
    },
    created() {
        if (auth.getUser().rol === "fieldOps") {
            this.$router.push('/report');
        } else if (auth.getUser().rol === "maintenance") {
            this.priorityFilter = '';
        }

        this.getAllReports();
    }
}
</script>

<style scoped>

    .rp-search-block.flex-center {
        justify-content: left;
        max-width: 100%;
        padding: 20px 0;
    }

    .rp-table-block {
        display: block;
        justify-content: center;
    }

    th {
        font-size: 1.5em;
        border-bottom: 1px solid #333; 
        border-right: 1px solid #333;
        padding:0 15px;
    }

    th:last-child, td:last-child {
        border-right: none;

    }

    td:first-child {
        background-color: #D5F0A6;
    }

    td:last-child {
        background-color: #F0B5A6;
    }

    td {
        padding:5px 15px;
        text-align: center;
        background-color: #fff;
        border-right: 1px solid #333;
        font-size: 1.5em;
    }

    select {
        width: 200px;
        font-size: 1.4em;
    }

    .select-block {
        margin-right: 10px;
    }

    label {
        font-size: 1.5em;
    }

    .label {
        font-size: 1em;
        line-height: normal;
    }

    .rp-info-block {
        display: flex;
        max-width: 100%;
        line-height: 1em;
        padding: 20px;
        border-top: solid 1px #333;
    }

    .rp-image img {
        width: 200px;
        text-align: left;
        margin-right: 20px;
    }

    .alert img{
        width: 15px;
        padding: 2px;

    }

    .priority-switch {
        display: flex;
        align-items: center;
    }

    .rp-detail {
        width: 600px;
    }
    
    .rp-single {
        font-size: 1.5em;
        padding: 5px 10px;
    }

    .summary {
        line-height: normal;
    }

    .flex-center {
        display: flex;
        align-items: center;
    }

    .flex-center.flex-end {
        width: 600px;
        justify-content: space-between;
    }

    .repair-block {
        padding: 5px 0;
    }

    .rp-priority {
        display: flex;
        max-width: 100%;
        font-size: 1.5em;
        padding: 0px 10px 0px;
        line-height: normal;
        justify-content: space-between;
    }

    .show-btn {
        border-radius: 5px;
        padding: 5px;
        font-size: 1em;
        background-color: #fff;
        color: #333;
        margin-right: 10px;
    }


    .form-btn {
        border-radius: 5px;
        padding: 5px;
        font-size: 1em;
        background-color: #333;
        color: #fff;
    }

    .form-btn:hover {
	color: #333;
	background-color: #fff;
	transition: background-color .3s;
    }

    .rp-single.flex-center {
        justify-content: space-between;
        align-items: flex-end;
    }

    .date-input {
        font-size: .9em;
        width: 150px;
    }

    .submit {
        border-radius: 5px;
        padding: 5px;
        font-size: 1em;
        background-color: #fff;
        color: #333;
        margin-left: 10px;
    }

    .submit:hover {
        background-color: #333;
        color: #fff;
    }

</style>