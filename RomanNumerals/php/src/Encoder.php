<?php

namespace RomanNumeral;

class Encoder
{
    private static $arabicMap = array(
        1 => 'I',
        4 => 'IV',
        5 => 'V',
        9 => 'IX',
        10 => 'X',
        40 => 'XL',
        50 => 'L',
        90 => 'XC',
        100 => 'C',
        400 => 'CD',
        500 => 'D',
        900 => 'CM',
        1000 => 'M'
    );

    public function encode($arabicNumber)
    {
        $romanNumer = '';
        foreach ($this->arabicMap() as $arabic => $roman) {
            while ($arabicNumber >= $arabic) {
                $romanNumer .= $roman;
                $arabicNumber -= $arabic;
            }
        }

        return $romanNumer;
    }

    private function arabicMap()
    {
        $map = static::$arabicMap;
        ksort($map);

        return array_reverse($map, true);
    }
}
