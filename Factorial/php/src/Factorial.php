<?php

final class Factorial
{
    public static function of($number)
    {
        if ($number === 0) {
            return 1;
        }

        return $number * static::of($number - 1);
    }
}
