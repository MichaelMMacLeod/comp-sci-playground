module Main where

main :: IO ()
main = do
    putStr "Input: "
    input <- readLn
    weights <- readFile "weights.txt"
    putStrLn $ "Result: " ++ show (makeNet input (read weights))

-- Returns the result of passing inputs through a neural network created with
-- the specified weights.
makeNet :: [Double] -> [[[Double]]] -> [Double]
makeNet inputs weights = makeNet' inputs weights act (length weights - 1)

-- Returns the result of passing inputs through a layer of neurons created with
-- the specified weights and activation function, f.
makeLayer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
makeLayer inputs weights f = makeLayer' inputs weights f 0

-- Returns the result of passing inputs into a neuron with the specified weights
-- and activation function, f.
neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- The logistic-sigmoid activation function.
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- The derivative of act.
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

-- The cost function.
cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2

-- Helper function for makeNet; not meant to be called by anthing other than
-- makeNet.
makeNet' :: [Double] -> [[[Double]]] -> (Double -> Double) -> Int -> [Double]
makeNet' inputs weights f n
    | n == 0    = makeLayer inputs (head weights) f
    | otherwise = makeLayer previous (weights !! n) f
    where
        previous = makeNet' inputs weights f (n-1)

-- Helper function for makeLayer; not meant to be called by anything other than
-- makeLayer.
makeLayer' :: [Double] -> [[Double]] -> (Double -> Double) -> Int -> [Double]
makeLayer' inputs weights f n
    | n == length weights = []
    | otherwise           = neuron inputs (weights !! n) f : next
    where
        next = makeLayer' inputs weights f (n+1)
