<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Batch extends Model
{
    use HasFactory;

    public function humidities(){
        return $this->hasMany(Humidity::class);
    }
    public function temperatures(){
        return $this->hasMany(Temperature::class);
    }
    public function states(){
        return $this->hasMany(State::class);
    }
}
