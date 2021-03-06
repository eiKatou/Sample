console.log("hello promise");

function getPromise() {
  return new Promise((resolve, reject) => {
    console.log("時間がかかる処理開始");
    setTimeout(() => {
      console.log("時間がかかる処理完了");
      resolve("OK");
    }, 1000);
  });
}

// getPromise().then(value => {
//   console.log(value);
// }).catch(error => {
//     console.error(error);
// });

getPromise()
  .then(value => console.log(value))
  .catch(error => console.error(error));

Promise.resolve(1)
  .then((value) => console.log(`値は${value}`))
  .then(() => console.log("終了"));