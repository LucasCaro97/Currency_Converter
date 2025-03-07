import { useEffect, useState } from 'react'
import './App.css'
import InputValue from './components/InputValue'
import SelectCurrency from './components/SelectCurrency'
import RefreshIcon from './icons/RefreshIcon'
import Result from './components/Result'

function App() {
  const [originCurrency, setOriginCurrency] = useState(0);
  const [finalCurrency, setFinalCurrency] = useState(0);
  const [amount, setAmount] = useState(0);
  const [conversionRate, setConversionRate] = useState(0);
  const [isFirstRender, setIsFirstRender] = useState(true);
  const [result, setResult] = useState(0);
  const [invertValues, setInvertVaules] = useState(false);


  const getOriginSelect = (event) => {
    setOriginCurrency(parseFloat(event.target.value))
  }
  
  const getFinalSelect = (event) => {
    setFinalCurrency(parseFloat(event.target.value))
  }

  const convertCurrency = () => {
    const newConversionRate = finalCurrency / originCurrency;
    setConversionRate(finalCurrency / originCurrency)
    setResult(amount * newConversionRate);
  }

  const handleInputChange = (inputValue) => {
    setAmount(parseFloat(inputValue))
  }

  const handleRefreshClick = () => {
    setInvertValues((prevInvert) => !prevInvert);
  };

  return (
    <>
      <div className='border-2 border-solid p-4 rounded-lg'>
      <h1 className="text-3xl font-bold text-white mb-8">
        Currency Conversor
      </h1>
      <div className='flex flex-col justify-center mb-4 lg:flex-row lg:items-center lg:gap-6 xl:flex-row'>
        <SelectCurrency className='mb-4 lg:mb-0  xl:mb-0 xl:w-1/2' textLabel='Origin Currency' onSelect={getOriginSelect}/>
        <div className='flex justify-center text-white'>
        <RefreshIcon/>
        </div>      
        <SelectCurrency className='mb-8 lg:mb-0 xl:mb-0 xl:w-1/2' textLabel='Final Currency' onSelect={getFinalSelect}/>
      </div>
        <InputValue className='mb-8 lg:mb-4 xl:mb-4' onChange={handleInputChange}/>      
        <button onClick={convertCurrency} className='bg-blue-900 rounded-xl w-36 mx-auto mb-2 lg:mb-0 xl:mb-0'>Convert</button>
        <Result amount={result} className="m-4 text-amber-600 text-2xl font-bold"/>
      </div>
    </>
  )
}

export default App
