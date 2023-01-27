//Helper method to set html button click listener when the webview is loaded
addEventListener("load", (event) => {
    document.getElementById("button").addEventListener("click", function () {
       getPosts(1);
    });
});

// Fetching posts from jsonplaceholder website
// getPosts method is called from 2 points with 2 different request params to see the difference:
// 1. button at the top of html
// 2. Fab button in Android
// both usages has the same result,
// text of the paragraph in html is set according to the response and
// it is also returned to the exampleJSMethod in ExampleJSInterface on Android site
function getPosts(postId) {
  const xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/" + postId);
  xhr.responseType = "json";
  xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      document.getElementById("counter").innerHTML = xhr.response.title;
      exampleJSInterface.exampleJSMethod(xhr.response.title);
    } else {
      document.getElementById("counter").innerHTML = "Error calling post";
      exampleJSInterface.exampleJSMethod("Error calling post");
    }
  };
  xhr.send();
}