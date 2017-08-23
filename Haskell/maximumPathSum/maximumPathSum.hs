main = do
    raw <- readFile "path.txt"
    let input = map (map read) (map words (lines raw)) :: [[Int]]
    print $ maxPathSum (reverse input)

maxPathSum ([x]:[]) = x
maxPathSum (x:y:xs) = maxPathSum (zipWith (+) (maximums x) y : xs)
    where 
        maximums (_:[])   = []
        maximums (x:y:xs) = max x y : maximums (y:xs)