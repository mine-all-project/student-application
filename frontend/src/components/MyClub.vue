<template>
  <div>
    <a-col class="flex-column" style="align-items: center">
      <a-input placeholder="Search My Club" style="width: 50%">
          <span slot="suffix">
            <a-icon type="search"/>
          </span>
      </a-input>
      <a-col class="border" style="width: 100%;height: 10vh;margin-top: 10px;padding: 5px">
        <h3>Maybe Interested Clubs</h3>
      </a-col>
      <a-col class="border" style="width: 100%;height: 30vh;margin-top: 10px;padding: 5px">
        <h3>My Favorite Club</h3>
      </a-col>
    </a-col>
  </div>
</template>
