Vue.component('templateOne',{

	template:'#templateOne',
	methods: {

		hide (){
			this.$emit('change',"s");
			
		}

	}


});

Vue.component('templateTwo',{

	template:'#templateTwo',
	methods: {

		hide (){
			this.$emit('change',"s");
			
		}

	}


});

var app = new Vue({
	el: rootParent,
	data: {
		componenetName:"",
		checkBox: [],
		modalShow: false,
		modalDelete:false,
		searchList: [],
		search: "",
		modalButtonOK: "",
		modalButtonNaziv: "",
		predavacDelteId:"",
		selectedPredavac: null,
		players: [],

		predavacNovo: {
			id: '',
			ime: "",
			pozicijaPredavaca: "",

		}
	},
	beforeCreate() {
		
	},

	mounted() {
		console.log("Mounted");
		this.fetchPlayers();
	},
	created() {

	},
	updated() {
		this.ispis();
		this.fieldCleaner();
		
		
	},
	destroyed() {
			console.log("Destroy");
	},

	computed: {

		list() {
			console.log("computed");
			var self = this;
			return this.searchList.filter(function (cust) {
				return cust.ime.toLowerCase().indexOf(self.search.toLowerCase()) >= 0;
			});
		},

	/*list() {
			console.log("computed");
			var self = this;
			return this.searchList.filter( cust=>
				cust.ime.toLowerCase().indexOf(self.search.toLowerCase()) >= 0
			);
		},*/

	},

	methods: {

		change(name){
			this.componenetName=name;
		},

		predavacScanner (predavac){
			this.selectedPredavac=predavac;
			//alert(predavac.ime);
		},
		
		fetchPlayers(){
                axios.get("/players").then(function(response){
					this.players = response.data;
					console.log(response.data);
				}.bind(this));
            },
		

		deletePredavac() {

			axios.delete("/predavac/" + this.predavacDelteId).then((response) => {
				this.predavacDelteId="";
				this.fetchPredavaci();
				//	window.location.reload();
				console.log(index);
			});
		},
		deletePredavacAll() {

			//[0,1,2]
			axios.post("/predavac/deleteAll", this.checkBox).then((response) => {
				
				
				
				this.fetchPredavaci();
				for (let i = 0; i < this.checkBox.length; i++) {
					this.checkBox.splice(i, this.checkBox.length);
					
				}
				console.log("Stanje checkBoxa :"+this.checkBox);
			
				
			});
		},
		openEditPredavac(predavac) {
			this.modalButtonOK = "Update";
			this.predavacNovo.id = predavac.id;
			this.predavacNovo.ime = predavac.ime;
			this.predavacNovo.pozicijaPredavaca = predavac.pozicijaPredavaca;
			this.modalShow = true;

		},
		showDeleteModal(id){
			this.predavacDelteId=id;
			this.modalDelete=true;
		},
		openAddPredavac() {

			this.modalButtonOK = "Add";
			this.modalShow = true;
		},
		fieldCleaner() {
			
			if (this.modalShow == false) {
			this.predavacNovo.id = "";
			this.predavacNovo.ime = "";
			this.predavacNovo.pozicijaPredavaca = "";
			console.log("brisem polja");
		}
		if(this.modalDelete==false){
			console.log("brisem id");
			this.predavacDelteId="";
		}
		},
		editAddPredavac(predavac) {

			
			if (this.modalButtonOK == "Add") {
				this.addPredavac(predavac);
				
			} else if (this.modalButtonOK == "Update") {
				this.editPredavac(predavac);
			}
		},

		editPredavac(predavac) {
			axios.put("/predavac/" + predavac.id, {
				id: predavac.id,
				ime: predavac.ime,
				pozicijaPredavaca: predavac.pozicijaPredavaca

			}).then((response) => {
				this.modalShow = true;
				console.log("edit uspio");
				this.fetchPredavaci();

			});

		},

		addPredavac(predavac) {
			if(predavac.ime){
			axios.post("/predavac/unos", {
				ime: predavac.ime,
				pozicijaPredavaca: predavac.pozicijaPredavaca
			}).then((response) => {
					
				console.log("add uspio");
				this.fetchPredavaci();

			}).catch(error =>{

				console.log(error);
			});
}else{
	
	console.log("-----------------ime prazno");
	this.modalShow = true;
}
		},
		ispis() {

			this.searchList.forEach(element => {
				console.log("Ispis :" + element.ime);
			});
		}

	}

});