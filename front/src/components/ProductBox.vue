<template>
  <div class="card h-100 w-100">
    <div class="embed-responsive embed-responsive-16by9">
      <img
        class="card-img-top embed-responsive-item"
        :src="product.imagemUrl"
        alt="Card image cap"
      />
    </div>
    <div class="card-body">
      <router-link :to="{ name: 'ShowDetails', params: { id: product.id } }">
        <h5 class="card-title">{{ product.nome }}</h5>
      </router-link>
      <p class="card-text">{{ product.descricao.substring(0, 65) }}...</p>
      <router-link
        :to="{ name: 'EditProduct', params: { id: product.id } }"
        v-show="$route.name == 'AdminProduct'"
      >
        <button class="btn btn-primary">Edit</button>
      </router-link>
      <button class="btn btn-danger ml-3" @click="deleteProduto(product.id)" >Remover</button>
    </div>
  </div>
</template>
<script>
import swal from "sweetalert";
import axios from "axios";
export default {
  name: "ProductBox",
  props: ["product"],
  methods: {
    deleteProduto(idProduto) {
      axios
        .delete(`http://localhost:8080/produtos/${idProduto}`)
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Produto removido com sucesso!",
              icon: "success",
            });
          }
        })
        .catch(() => {
              swal({
              text: "Não foi possível remover!",
              icon: "error",
            });
        });
      //location.reload();
    },
  },
};
</script>
<style scoped>
.card-img-top {
  object-fit: cover;
}
a {
  text-decoration: none;
}
.card-title {
  color: #484848;
}
</style>
