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

makeLayer :: [Double] -> [[Double]] -> (Double -> Double) -> Int -> [Double]
makeLayer inputs weights f n
    | n == length weights = []
    | otherwise           = neuron inputs (weights !! n) f : next
    where
        next = makeLayer inputs weights f (n+1)

makeNet :: [Double] -> [[[Double]]] -> (Double -> Double) -> Int -> [Double]
makeNet inputs weights f n
    | n == 0    = makeLayer inputs (head weights) f 0
    | otherwise = makeLayer previous (weights !! n) f 0
    where
        previous = makeNet inputs weights f (n-1)


-- placeholder for a more advanced function
-- only works with one hidden node and one output node
net :: [Double] -> [[Double]] -> (Double -> Double) -> Double
net input weights f =
    neuron [neuron input (head weights) act] (last weights) act

neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- activation function
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- derivative of the activation function
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

-- calculates error
cost :: Num a => [a] -> [a] -> [a]
cost = zipWith (-)
