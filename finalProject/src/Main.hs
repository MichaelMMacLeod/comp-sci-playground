module Main where

main :: IO ()
main = print . show $ net [1,2,3] [[0.2,0.3,0.7],[0.5]] act

-- placeholder for a more advanced function
-- only works with one hidden node and one output node
net :: [Double] -> [[Double]] -> (Double -> Double) -> Double
net input weights f =
    neuron [neuron input (head weights) act] (last weights) act

neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = sum $ map f (zipWith (*) inputs weights)

-- activation function
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- derivative of the activation function
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

-- calculates error
cost :: Num a => [a] -> [a] -> [a]
cost = zipWith (-)
