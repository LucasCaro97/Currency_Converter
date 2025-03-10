import { useEffect, useState } from "react"
import axios from 'axios'


export default function SelectCurrency({textLabel, className, onSelect}) {
  
  const apiUrl = import.meta.env.VITE_API_URL;


  const handleChange = (event) => {
    onSelect(event);
  }

  const [currencies, setCurrencies] = useState([])

  useEffect( () => {
    const getAllCurrencies  = async () => {
      try{
        console.log(`${apiUrl}/currency/getAll`)
        const respuesta = await axios.get(`${apiUrl}/currency/getAll`);    
        const datos = await respuesta.data;
        setCurrencies(datos);
      }catch(error){
        console.error("Error while get currencies from database: " + error)
      }
    };
    
    getAllCurrencies();
  }, [])
  
  return (
    <div className={className}>
        <label className="flex text-sm font-medium leading-6 text-gray-300">{textLabel}</label>
        <select
        name="currency"
        className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
        onChange={handleChange}
        >
          <option>Select Currency</option>
          {currencies.map((currency) => (
            <option key={'currency'+currency.id} value={currency.value}>{currency.name}</option>
          ))}    
        </select>
    </div>
  )
}
