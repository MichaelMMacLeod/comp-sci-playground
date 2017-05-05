module Main where

main :: IO ()
main = print . show $ makeNet inputs weights act (length weights - 1)

inputs = [1]

weights =
    [
        [
            [0.1],
            [0.2]
        ],
        [
            [0.3,0.4]
        ]
    ]

-- Creates a layer of neurons using specified inputs, weights, and the
-- activation function, f.
makeLayer :: [Double] -> [[Double]] -> (Double -> Double) -> Int -> [Double]
makeLayer inputs weights f n
    | n == length weights = []
    | otherwise           = neuron inputs (weights !! n) f : next
    where
        next = makeLayer inputs weights f (n+1)

-- Creates a network of neurons using specified inputs, weights, and the
-- activation function, f.
makeNet :: [Double] -> [[[Double]]] -> (Double -> Double) -> Int -> [Double]
makeNet inputs weights f n
    | n == 0    = makeLayer inputs (head weights) f 0
    | otherwise = makeLayer previous (weights !! n) f 0
    where
        previous = makeNet inputs weights f (n-1)

neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- activation function
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- derivative of the activation function
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2
