module Main where

main :: IO ()
main = do
    putStr "Input: "
    input <- readLn
    weights <- readFile "weights.txt"
    putStrLn $ "Result: " ++ show (getNet input (read weights) act)

-- Returns the value of each neuron in a neural network created with the
-- specified inputs, weights, and activation function, f.
getNet :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
getNet inputs weights f
    | null weights = []
    | otherwise    = currentLayer : nextLayers
    where
        currentLayer = makeLayer inputs (head weights) f
        nextLayers   = getNet currentLayer (tail weights) f

-- Returns the value of each neuron in a neuron layer created with the specified
-- inputs, weights, and activation function, f.
makeLayer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
makeLayer inputs weights f
    | null weights = []
    | otherwise    = currentNeuron : nextNeuron
    where
        currentNeuron = neuron inputs (head weights) f
        nextNeuron    = makeLayer inputs (tail weights) f

-- Returns the value of a neuron created with the specified inputs, weights, and
-- activaiton function, f.
neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- A logistic activation function.
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- The derivative of act.
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2
