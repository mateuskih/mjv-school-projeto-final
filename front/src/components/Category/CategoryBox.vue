<template>
  <div class="card w-100 h-100" style="margin-top: 10px">
    <div class="embed-responsive embed-responsive-16by9">
      <img
        class="card-img-top embed-responsive-item"
        :src="category.imagemUrl"
        alt="Card image cap"
      />
    </div>
    <div class="card-body">
      <router-link :to="{name: 'ListProducts', params: {id: category.id} }">
        <h5 class="card-title">{{ category.nome }}</h5>
      </router-link>
      <p class="card-text">
        {{ category.descricao }}
      </p>
      <router-link :to="{name: 'EditCategory', params: {id: category.id}}"
                   v-show="$route.name == 'Category'">
        <button class="btn btn-primary">Edit</button>
      </router-link>
      <button class="btn btn-danger ml-3" @click="deleteCategory(category.id)" v-show="$route.name == 'Category'">Remover</button>
    </div>
  </div>
</template>
<script>
import swal from "sweetalert";
import axios from "axios";
export default {
  name: "CategoryBox",
  props: ["category"],
  methods: {
    deleteCategory(idCategoria) {
      axios
        .delete(`http://localhost:8080/categorias/${idCategoria}`)
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Categoria removida com sucesso!",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
      location.reload();
    }
  },
};
</script>

<style scoped>
.card-img-top {
  object-fit: cover;
}
</style>
