const express = require('express')

const app = express()
const port = 8889
app.use(express.json())

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.post('/', (req, res) => {
  res.send({ status: 'SUCCESS' });
  console.log(req.body);
  const appName = JSON.stringify(req.body);
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})