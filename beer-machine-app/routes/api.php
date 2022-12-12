<?php

use App\Http\Controllers\APIController;
use App\Http\Controllers\ProductController;
use App\Http\Resources\BatchResource;
use App\Models\Batch;
use App\Models\Humidity;
use App\Models\Temperature;
use App\Models\vibration;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Session;
use Illuminate\Support\Facades\Storage;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('posttest',[ProductController::class,'updateData']);
Route::get('testing',[ProductController::class,'resourceFunction']);

Route::get('endpoint', [ProductController::class,'startCmd']);
Route::get('collection/{batch}',[ProductController::class,'getEverything']);
