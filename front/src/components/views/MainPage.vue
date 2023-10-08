<script setup>
  import Card from "primevue/card"
  import InputText from "primevue/inputtext";
  import Button from "primevue/button";
  import Message from "primevue/message";
  import { useToast } from "primevue/usetoast";
  import {ref} from "vue";
  import API from "@/api"

  const inputUrl = ref("");

  const shortUrl = ref(null);

  const isInvalidUrl = ref(false);

  const message = ref([]);

  const toastSvc = useToast();

  const invalidUrlErrorMessage =
      "Invalid URL. Make sure your URL is in the following format: scheme://host/path?query#fragment\n Example: " +
      "https://www.google.com"

  // cardPassThrough is used to control the style of the Card component`s inner elements with the pt prop
  // this was used to allow for better flex control inside the component
  const cardPassThrough = {
    body: {
      style: {
        'box-sizing': 'border-box',
        'padding-left': '0',
        'padding-right': '0',
        'padding-bottom': '0'
      }
    },
    content: {
      style: {
        'height': '100%',
        'padding-bottom': '0'
      }
    }
  }

  const shortify = async () => {
    // TODO: Loading modal
    try {
      const response = await API.axios.post("/shortify/api/v1/uri-record/create", {
        "uri": inputUrl.value
      });

      shortUrl.value = response.headers.get("location");
      isInvalidUrl.value = false;

      setErrorMessageVisibility(false);
    } catch (httpError) {
      console.error("Request error", httpError);
      isInvalidUrl.value = true;
      setErrorMessageVisibility(true);
    }
  }

  const copyShortUrl = async () => {
    await navigator.clipboard.writeText(shortUrl.value)
        .then(() => {
          toastSvc.removeGroup("clipboardCopy");
          toastSvc.add({
            severity: "success",
            summary: "Short URL copied",
            detail: "The short URL has been copied to your clipboard!",
            life: 10000,
            group: "clipboardCopy"
          });
        })
        .catch((error) => {
          console.error(
              "Error when copying short URL to clipboard. Please contact the developer at https://github.com/Henriqueapan/",
              error
          );
          toastSvc.add({
            severity: "error",
            summary: "Short URL was not copied",
            detail: "An error occurred when trying to copy the short URL to your clipboard! Please contact the " +
                "developer at https://github.com/Henriqueapan/",
            life: 10000,
            group: "clipboardCopy"
          })
        });
  }

  const setErrorMessageVisibility = (on) => {
    message.value = on ? [{ severity: "error", content: invalidUrlErrorMessage }] : [];
  }
</script>

<template>
  <div class="text-center sm:text-7xl text-6xl mt-8 mb-8 font-semibold text-color-accent">SHORTIFY</div>

  <div id="main-card--wrapper" class="flex justify-content-center">
    <Card id="main-card" :pt="cardPassThrough">
      <template #content>

        <div class="flex flex-column h-full" id="main-card--content">

          <div class="px-3">

            <div class="field mb-3">
              <label class="font-semibold text-2xl w-full text-color-secondary">URL
              <InputText
                  v-model="inputUrl"
                  placeholder="https://long-url.com"
                  :class="`w-full mt-1 h-3rem text-lg ${isInvalidUrl ? 'p-invalid' : ''}`"
              ></InputText>
              </label>
                <Message v-for="msg of message" :key="msg.id" :severity="msg.severity" @close="setErrorMessageVisibility(false)">{{ msg.content }}</Message>
            </div>

            <div class="field text-center pb-2">
              <Button label="SHORTIFY URL" class="confirm-button font-spacing" @click="shortify" rounded size="large"></Button>
            </div>

          </div>

          <div v-if="shortUrl" class="w-full" id="main-card--result">
            <Button class="text-xl sm:text-5xl text-white font-medium w-full h-full py-3 sm:py-4 justify-content-center" text @click="copyShortUrl">
              <span >{{ shortUrl }}</span>
              <i class="pi pi-copy text-2xl sm:text-5xl absolute" style="width:0; right: 10%"></i>
            </Button>
          </div>

        </div>

      </template>
    </Card>
  </div>
</template>

<style scoped>

.p-card .p-card-body {
  padding: 0 !important;
}

@media (max-width: 320px) {
  .xs-text-2xl {
    font-size: 1.5rem !important;
  }
}

#main-card {
  max-height: 360px;
  flex-basis: 860px;
  margin: 0 24px 0 24px;
  border: solid 3px;
  border-color: var(--tertiary);
  background-color: var(--secondary)
}

#main-card--result {
  background-color: var(--accent);
  border-top: 3px solid;
  border-top-color: var(--tertiary);
  border-radius: 0 0 4px 4px;
}
</style>
