
import './App.css'
import MyDrawer from './components/MyDrawer'
import MainContent from './components/MainContent'
// import DemoApi from './components/DemoApi'

function App() {
 

  return (
    <>
      <MyDrawer>
        <MainContent />
         
      </MyDrawer>
      {/* <DemoApi/>   for testing purpose! */}
    </>
  )
}

export default App
