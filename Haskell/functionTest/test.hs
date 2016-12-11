fac 0 = 1
fac n = n * fac (n - 1)

main = do
    x <- readLn
    putStrLn (show (fac x))