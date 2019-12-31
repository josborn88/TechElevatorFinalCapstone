<template>
  <div id="report" class="text-center">
     <div class="wrapper">
      <h1 class="form-title">Report a Risk</h1>
    <form class="form-block" > 
        <div role="alert" v-if="reportErrors">
            There was an unexpected Error. 
        </div>
        <div class="risk-block wrapper">
        <div class="fm-data">
        <label for="file" class="fm-label">Upload Picture:</label>
        <input type="file" ref="image" @change="handleFileChange" class="fm-label wrapper fm-txt file-input"/>
        </div>

        <div class="fm-data">
        <label for="riskReporter" class="fm-label">Reporter Name:</label>
        <input
         type="text"
         id="reporterName"
         class="fm-txt report"
         v-model="report.riskReporter"
         required
        />
        </div>

        <div class="fm-data">
        <label for="client" class="fm-label">Client Name:</label>
        <input
         type="text"
         id="client"
         class="fm-txt report"
         v-model="report.clientName"
         required
         />
        </div>

        <div class="fm-data">
        <label for="name" class="fm-label">Risk Name:</label>
        <input
         type="text"
         id="name"
         class="fm-txt report"
         v-model="report.name"
         required
        />
        </div>
       
        <div class="fm-data">
        <label for="location" class="fm-label">Location of Risk:</label>
        <input
         type="text"
         id="location"
         class="fm-txt report"
         v-model="report.locationName"
         required
         />
        </div>

         <div class="fm-data">
        <label for="address" class="fm-label">Address of Location:</label>
        <input
         type="text"
         id="address"
         class="fm-txt report"
         v-model="report.locationAddress"
         required
         />
        </div>

        <div class="fm-data">
         <label for = "summary" class="fm-label">Summary of Vulnerability:</label>
        <textarea
         type="text"
         id="summary"
         class="fm-txt summary"
         v-model="report.summary"
         required
         />
        </div>
        <div class="btn-block">
         <button v-on:click.prevent="addReport()" @click="uploadImage" class="btn">Submit</button>
        </div>
        </div>
    </form>
    </div>
  </div>

</template>

<script>
import auth from '../auth'

export default {
    name: 'report',
    data() {
        return {
        API_URL: "http://localhost:8080/AuthenticationApplication/api", 
         report: {
            clientName: '',
            name: '',
            locationName: '',
            summary: '',
            riskReporter: '',
            imageName: '',
            locationAddress: ''
            },
            reportErrors: false,

            file: null
            };
        },
        methods: {
            addReport() {
                this.report.imageName = this.file.name;
                fetch(`${this.API_URL}/report`,
                 {
                    method: 'POST',
                    headers: {
                         "Content-Type": "application/json",
                         'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: JSON.stringify(this.report)

                })
                .then((response) => {
                    if(response.ok) {
                        console.log(this.file.name);
                        console.log(response);
                        this.clearForm();
                    } else {
                        this.reportErrors = true;
                    }
                })
                .catch((err) => console.error(err));
            },
            clearForm() {
                this.report.name="";
                this.report.riskReporter="";
                this.report.imageName="";
                this.report.locationName="";
                this.locationAddress="";

                this.report.summary="";
                this.resetFileInput();
                alert("successful submit");
            },
            resetFileInput() {
                const input = this.$refs.image
                input.type = 'text'
                input.type = 'file'
            },

             handleFileChange(evt) {
                console.log(evt, evt.target.files);
                const file = evt.target.files[0];
                console.log(file);
                this.file = file;
            },
            uploadImage() {
                console.log("upload image");
                const formData = new FormData();
                formData.append("file", this.file);
                formData.append(
                    "test",
                    new Blob([JSON.stringify({ id: 1, name: "test" })], {
                    type: "application/json"
                    })
                );
                fetch(`${this.API_URL}/photos/with-data`, {
                    method: "POST",
                    headers: {
                        'Authorization': 'Bearer ' + auth.getToken(),
                    },
                    body: formData
                })
                    .then(response => console.log(response))
                    .catch(err => console.error(err));
            },

        },
};
</script>
<style scoped>

    .fm-data {
        width: 600px;
        margin: auto;
        align-items: center;
    }

    .form-title {
        padding-bottom: 30px;
    }

    .file-input {
        width: 404px;
        margin: auto 0;
        justify-content: center;
        align-content: center;
        justify-items: center;
    }

    .btn-block {
        margin-left: 175px;
    }

</style>