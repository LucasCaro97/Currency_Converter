import { useEffect, useState } from "react"

export default function Result({className, amount, onAmountChange}) {
    const [formattedAmount, setFormattedAmount] = useState(0);

    useEffect( () => {
        setFormattedAmount(amount);
    }, [amount] )
    
    return (
      <div className={className}>
        <span>${formattedAmount.toLocaleString()}</span>
      </div>
    )
  }
  