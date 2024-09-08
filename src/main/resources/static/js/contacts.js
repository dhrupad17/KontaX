console.log("Contacts.js");
const baseURL = "http://localhost:8081";

const viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      contactModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_modal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

const swalWithTailwindButtons = Swal.mixin({
  customClass: {
    confirmButton: "bg-red-500 text-white font-bold py-2 px-4 rounded hover:bg-red-600 mx-2", // Added margin on both sides
    cancelButton: "bg-gray-300 text-black font-bold py-2 px-4 rounded hover:bg-gray-400 mx-2" // Added margin on both sides
  },
  buttonsStyling: false
});

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

async function loadContactdata(id) {
  //function call to load data
  console.log(id);
  try {
    const data = await (await fetch(`${baseURL}/api/contacts/${id}`)).json();
    console.log(data);
    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_image").src = data.picture;
    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_about").innerHTML = data.description;
    const contactFavorite = document.querySelector("#contact_favorite");
    if (data.favourite) {
      contactFavorite.innerHTML =
        "<i class='fas fa-star text-yellow-400'></i> &nbsp; &nbsp; <span>This Contact is marked Favourite.</span>";
    } else {
      contactFavorite.innerHTML = "This Contact is not marked as Favourite.";
    }

    document.querySelector("#contact_website").href = data.websiteLink;
    document.querySelector("#contact_website").innerHTML = data.websiteLink;
    document.querySelector("#contact_linkedIn").href = data.linkedInLink;
    document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink;
    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}


  //delete Contact

  async function deleteContact(id) {
    swalWithTailwindButtons.fire({
      title: "Do you want to permanently delete the contact?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Delete",
      cancelButtonText: "Cancel",
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        const url = `${baseURL}/user/contacts/delete/` + id;
        window.location.replace(url);
      }
    });
}


