module Main where

main :: IO ()
main = do
    putStr "Input: "
    input <- readLn
    weights <- readFile "weights.txt"
    putStrLn $ "Result: " ++ show (getAllLayers input (read weights) act)

getAllLayers :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
getAllLayers inputs weights f
    | length weights == 1 = [makeLayer inputs (head weights) f]
    | otherwise           = currentLayer : previousLayers
    where
        currentLayer   = makeLayer (last previousLayers) (last weights) f
        previousLayers = getAllLayers inputs (init weights) f

makeLayer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
makeLayer inputs weights f = makeLayer' inputs weights f 0

neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

act' :: Floating a => a -> a
act' n = act n * (1 - act n)

cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2

makeLayer' :: [Double] -> [[Double]] -> (Double -> Double) -> Int -> [Double]
makeLayer' inputs weights f n
    | n == length weights = []
    | otherwise           = neuron inputs (weights !! n) f : next
    where
        next = makeLayer' inputs weights f (n+1)
